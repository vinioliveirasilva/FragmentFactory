package com.example.common.network

import com.google.gson.Gson

class RestException(vo: DefaultRestResponse?) :
    RuntimeException(Gson().toJson(vo))