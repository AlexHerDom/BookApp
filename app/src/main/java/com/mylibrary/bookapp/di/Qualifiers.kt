package com.mylibrary.bookapp.di

import javax.inject.Qualifier

@Qualifier
annotation class BookDataSourceQualifier

@Qualifier
annotation class FakeBookDataSourceQualifier

@Qualifier
annotation class EventDataSourceQualifier

@Qualifier
annotation class FakeEventDataSourceQualifier