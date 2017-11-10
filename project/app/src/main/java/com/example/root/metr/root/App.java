package com.example.root.metr.root;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.root.metr.data.MetrApi;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static App INSTANCE;
    private static final String BASE_URL="";
    private static volatile MetrApi mAPIServiceInstance;

    public static MetrApi getApiService() {
        MetrApi localInstance = mAPIServiceInstance;
        if (localInstance == null) {
            synchronized (MetrApi.class) {
                localInstance = mAPIServiceInstance;
                if (localInstance == null) {
                    Retrofit retrofit = App.getRetrofit();
                    mAPIServiceInstance = localInstance = retrofit.create(MetrApi.class);
                }
            }
        }
        return localInstance;
    }

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.addInterceptor (new ChuckInterceptor(INSTANCE));
        builder.addNetworkInterceptor(new StethoInterceptor());

        OkHttpClient client = builder.build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE=this;

        Fresco.initialize(this);

        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
