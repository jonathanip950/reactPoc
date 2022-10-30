package com.jebsen.api.beverageWebAppPoc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = {"http://localhost:*", "http://192.168.*", "http://10.0.0.*", "https://*.jebsen.com"})
public class BaseController {
}
