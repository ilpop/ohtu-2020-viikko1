package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuus(){
        varasto = new Varasto(-5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void Varasto() {
        varasto = new Varasto(15,6);
        assertEquals(15, varasto.getTilavuus(), vertailuTarkkuus);
         assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void VarastoTilavuusNegatiivinen() {
        varasto = new Varasto(-1,5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void VarastoSaldoNegatiivinen() {
        varasto = new Varasto(5, -5);
            assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void laitetaanLiikaaTavaraa() {
        varasto.lisaaVarastoon(50);
        
        // yritetään laittaa liikaa tavaraa, eli tavaran määrän ei pidä lisääntyä
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanEnemmanKuinmitaOn() {
        varasto.otaVarastosta(20);
        
        //yritetään ottaa enemään mitä varastossa on
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void lisaaNegatiivinenVarastoon() {
        varasto.lisaaVarastoon(-5);
        assertEquals(9, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void otaNegatiivinenVarastosta() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(-5);
        
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toSttring() { 
        assertEquals("saldo = " + varasto.getSaldo()   + ", vielä tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
    } 

}