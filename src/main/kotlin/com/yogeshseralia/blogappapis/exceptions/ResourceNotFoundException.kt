package com.yogeshseralia.blogappapis.exceptions

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class ResourceNotFoundException(
        val resource: String,
        val identifierName: String,
        val identifierValue: Int) :
        RuntimeException(String.format("%s not found with %s : %s",
                resource,
                identifierName,
                identifierValue
        ))
