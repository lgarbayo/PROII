/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.practicobloque2_1;

/**
 *
 * @author Luis
 */
public enum TipoSuelo {
    RURAL(0.35),
    URBANO(0.25),
    MIXTO(0.2);
    
    public double ibi;
    
    private TipoSuelo(double ibi) {
        this.ibi = ibi;
    }
}
