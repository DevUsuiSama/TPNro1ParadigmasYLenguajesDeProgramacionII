/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.systemengineering.tp1paradigmaylenguajesdeprogramacionii.util;

import java.text.DecimalFormat;

/**
 *
 * @author José Fernando Usui y Luciana Rojas
 */
public class Formato {
    
    public static String formatearDecimalEnPrecio(double precio) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(precio);
    }
    
}
