package com.example.nbateamviewer.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nbateamviewer.data.NBAData
import com.example.nbateamviewer.internal.NoConnectivityException

class NBADataSourceImpl(
    private val nbaApiService: NBAApiService
) : NBADataSource {

    private val _downloadedNBAData = MutableLiveData<NBAData>()

    override val downloadedNBAData: LiveData<NBAData>
        get() = _downloadedNBAData

    override suspend fun fetchNBAData() {
        try {
            val fetchedNBAData = nbaApiService
                .getNBAData()
                .await()
            _downloadedNBAData.postValue(fetchedNBAData)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}