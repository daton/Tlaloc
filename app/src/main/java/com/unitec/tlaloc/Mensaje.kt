package com.unitec.tlaloc

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Mensaje {
    var titulo:String?=null
    var cuerpo:String?=null
}