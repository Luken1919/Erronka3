package Erronka_3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaketeaUITest {

    private PaketeaUI paketeaUI;

    @BeforeEach
    public void setUp() {
        // Crear una instancia de PaketeaUI antes de cada prueba
        paketeaUI = new PaketeaUI();
    }

    

    @Test
    public void testFrameTitle() {
        // Verificar el título del frame
        assertEquals("Paketeen informazioa", paketeaUI.getTitle());
    }

    @Test
    public void testFrameDefaultCloseOperation() {
        // Verificar la operación de cierre del frame
        assertEquals(JFrame.DISPOSE_ON_CLOSE, paketeaUI.getDefaultCloseOperation());
    }

    @Test
    public void testFrameDimensions() {
        // Verificar las dimensiones del frame
        assertEquals(1000, paketeaUI.getWidth());
        assertEquals(750, paketeaUI.getHeight());
    }

    @Test
    public void testPanelLayouts() {
        // Verificar que los paneles tienen un diseño nulo
        JPanel pnlGuztia = (JPanel) paketeaUI.getContentPane();
        assertNull(pnlGuztia.getLayout());

        JPanel pnlZerrendaPanela = (JPanel) pnlGuztia.getComponent(1);
        assertNull(pnlZerrendaPanela.getLayout());

        JPanel pnlPaketeaHistoriala = (JPanel) pnlGuztia.getComponent(2);
        assertNull(pnlPaketeaHistoriala.getLayout());
    }

    @Test
    public void testButtonsActions() {
        // Simular clic en el botón "Banatzailea" y verificar el comportamiento
        JPanel pnlMenu = (JPanel) paketeaUI.getContentPane().getComponent(0);
        JButton btnBanatzailea = (JButton) pnlMenu.getComponent(1);
        btnBanatzailea.doClick();
        assertFalse(paketeaUI.isVisible()); // El frame original debe estar cerrado

        // Simular clic en el botón "Paketeak Esleitu" y verificar el comportamiento
        JButton btnPaketeaEsleitu = (JButton) pnlMenu.getComponent(2);
        btnPaketeaEsleitu.doClick();
        assertFalse(paketeaUI.isVisible()); // El frame original debe estar cerrado
    }
}
