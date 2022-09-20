package com.arif.testapi.payloads.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {

    private String fileName;
    private String message;

}
