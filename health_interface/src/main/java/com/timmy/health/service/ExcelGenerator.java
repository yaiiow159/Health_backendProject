package com.timmy.health.service;

import java.io.ByteArrayInputStream;
import java.util.Map;

public interface ExcelGenerator {
    ByteArrayInputStream generateExcel(Map<String, Object> data);
}
