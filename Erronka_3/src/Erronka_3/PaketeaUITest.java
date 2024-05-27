package Erronka_3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Container;

import javax.swing.JButton;
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
    public void testLabelsAndButtons() {
        // Obtener el panel principal
        JPanel pnlGuztia = (JPanel) paketeaUI.getContentPane();

        // Obtener y comprobar el texto del JLabel lblErabiltzailea
        JLabel lblErabiltzailea = findLabelByText(pnlGuztia, "Erabiltzailea: ");
        assertNotNull(lblErabiltzailea);
        assertTrue(lblErabiltzailea.getText().startsWith("Erabiltzailea: "));

        // Obtener y comprobar el botón btnBanatzailea
        JButton btnBanatzailea = findButtonByText(pnlGuztia, "Banatzailea");
        assertNotNull(btnBanatzailea);
        assertEquals("Banatzailea", btnBanatzailea.getText());

        // Obtener y comprobar el botón btnPaketeaEsleitu
        JButton btnPaketeaEsleitu = findButtonByText(pnlGuztia, "Paketeak Esleitu");
        assertNotNull(btnPaketeaEsleitu);
        assertEquals("Paketeak Esleitu", btnPaketeaEsleitu.getText());

        // Simular clic en btnBanatzailea y verificar la visibilidad del nuevo frame
        btnBanatzailea.doClick();
        assertFalse(paketeaUI.isVisible()); // El frame original debe estar cerrado

        // Volver a crear la instancia para continuar con más pruebas
        paketeaUI = new PaketeaUI();
    }
    
    



    // Método auxiliar para encontrar un JLabel por su texto
    private JLabel findLabelByText(JPanel panel, String text) {
        for (var component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().startsWith(text)) {
                    return label;
                }
            } else if (component instanceof JPanel) {
                JLabel label = findLabelByText((JPanel) component, text);
                if (label != null) {
                    return label;
                }
            }
        }
        return null;
    }

    // Método auxiliar para encontrar un JButton por su texto
    private JButton findButtonByText(JPanel panel, String text) {
        for (var component : panel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(text)) {
                    return button;
                }
            } else if (component instanceof JPanel) {
                JButton button = findButtonByText((JPanel) component, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    // Método auxiliar para encontrar un JList por su nombre
    private JList<?> findJListByName(JPanel panel, String name) {
        for (var component : panel.getComponents()) {
            if (component instanceof JList) {
                JList<?> jList = (JList<?>) component;
                if (jList.getName() != null && jList.getName().equals(name)) {
                    return jList;
                }
            } else if (component instanceof JPanel) {
                JList<?> jList = findJListByName((JPanel) component, name);
                if (jList != null) {
                    return jList;
                }
            }
        }
        return null;
    }
}