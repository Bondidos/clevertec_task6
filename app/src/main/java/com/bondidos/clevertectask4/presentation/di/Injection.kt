package com.bondidos.clevertectask4.presentation.di

import com.bondidos.clevertectask4.data.repository.RepositoryImpl
import com.bondidos.clevertectask4.presentation.ui.map_fragment.MapsFragment
import com.bondidos.clevertectask4.data.atmApi.BelBankService
import com.bondidos.clevertectask4.domain.constants.Constants.BASE_URL
import com.bondidos.clevertectask4.domain.Repository
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Component(modules = [DataModule::class, Binding::class])
interface AppComponent {
    fun inject(fragment: MapsFragment)
}

@Module
interface Binding {
    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}

@Module
object DataModule {

    @Provides
    fun provideBelBankApi(retrofit: Retrofit): BelBankService = retrofit
        .create(BelBankService::class.java)

    @Provides
    fun provideConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create()

    @Provides
    fun provideRxJavaFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        xJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(xJava2CallAdapterFactory)
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(BASE_URL)
            .build()
    }
}