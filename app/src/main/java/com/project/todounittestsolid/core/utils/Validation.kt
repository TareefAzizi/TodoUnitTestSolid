package com.project.todounittestsolid.core.utils

import com.project.todounittestsolid.data.model.FieldAndArg

object Validation {
    fun validate(vararg vs: FieldAndArg):Pair<Boolean,String>{
        vs.forEach {
            if (it.value.isEmpty()){
                return Pair(false,"${it.name} can't be empty")
            }
            if(it.reg != null){
                if(!Regex(it.reg).matches(it.value)){
                    return Pair(false,"Please enter a valid ${it.name}")
                }
            }
        }
        return Pair(true,"")
    }
}