package br.com.ufs.ds3.customization;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.assertj.swing.core.GenericTypeMatcher;

public class TypeMatcherHelper {

    private static TypeMatcherHelper INSTANCE;


    private TypeMatcherHelper() {
    }

    public static TypeMatcherHelper getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new TypeMatcherHelper();
        }
        return INSTANCE;
    }

    private int countPosition = 0;


    /*
     * Encontra um botão ativo com texto especifico
     */
    public GenericTypeMatcher<JButton> getActiveButton(final String text) {
        GenericTypeMatcher<JButton> textMatcher = new GenericTypeMatcher<JButton>(JButton.class) {

            @Override
            protected boolean isMatching(JButton component) {
                if (component.getText().toLowerCase().equals(text.toLowerCase()) && component.isEnabled()) {
                    return true;
                }
                return false;
            }
        };
        return textMatcher;
    }

    /*
     * Encontra um combobox que tenha uma das linhas com texto especifico
     */
    public GenericTypeMatcher<javax.swing.JComboBox> getComboBoxByValue(final String text) {
        GenericTypeMatcher<javax.swing.JComboBox> textMatcher = new GenericTypeMatcher<javax.swing.JComboBox>(
                javax.swing.JComboBox.class) {

            @Override
            protected boolean isMatching(javax.swing.JComboBox textField) {
                int size = textField.getItemCount();
                for (int i = 0; i < size; i++) {
                    String fieldValue = textField.getItemAt(i).toString();
                    if (text.replace(" ", "").toLowerCase().equals(fieldValue.toLowerCase().replace(" ", ""))) {
                        return true;
                    }
                }
                return false;
            }
        };
        return textMatcher;
    }

    /*
    * Encontra um JList que tenha uma das linhas com texto especifico
    */
    public GenericTypeMatcher<javax.swing.JList> getJlistStringByItemValue(final String value) {
        GenericTypeMatcher<javax.swing.JList> textMatcher = new GenericTypeMatcher<javax.swing.JList>(
                javax.swing.JList.class) {

            @Override
            protected boolean isMatching(JList component) {
                ListModel model = component.getModel();

                for (int i = 0; i < model.getSize(); i++) {
                    Object o = model.getElementAt(i);
                    if (!(o instanceof String)) {
                        break;
                    }
                    if (value.replace(" ", "").toLowerCase().equals(((String) o).toLowerCase().replace(" ", ""))) {
                        return true;
                    }
                }
                return false;
            }
        };
        return textMatcher;
    }

    /*
     * encontra unico JTextField ativo na tela
     */
    public GenericTypeMatcher<JTextField> getActiveTextArea() {
        GenericTypeMatcher<JTextField> textMatcher = new GenericTypeMatcher<JTextField>(JTextField.class) {

            @Override
            protected boolean isMatching(JTextField component) {
                if (component.isEnabled()) {
                    return true;
                }
                return false;
            }
        };
        return textMatcher;
    }

    /*
     * Encontra o botao correspondente a posicao pos e com texto text
     * Para casos em que temos mais de um botao igual na tela
     */
    public GenericTypeMatcher<? extends JButton> getActiveButtonByPositionAndText(String text, int pos) {
        countPosition = 0;
        GenericTypeMatcher<JButton> textMatcher = new GenericTypeMatcher<JButton>(JButton.class) {

            @Override
            protected boolean isMatching(JButton component) {
                if (component.getText().toLowerCase().equals(text.toLowerCase()) && component.isEnabled()) {
                    countPosition++;
                    if (countPosition == pos) {
                        return true;
                    }
                }
                return false;
            }
        };
        return textMatcher;
    }

    public GenericTypeMatcher<? extends JButton> getButtonByText(String text) {
        countPosition = 0;
        GenericTypeMatcher<JButton> textMatcher = new GenericTypeMatcher<JButton>(JButton.class) {

            @Override
            protected boolean isMatching(JButton component) {
                if (component.getText().toLowerCase().equals(text.toLowerCase()) && component.isEnabled()) {
                    return true;
                }
                return false;
            }
        };
        return textMatcher;
    }

    public GenericTypeMatcher<? extends JLabel> getLabelbyName(String name) {
        GenericTypeMatcher<JLabel> textMatcher = new GenericTypeMatcher<JLabel>(JLabel.class) {

            @Override
            protected boolean isMatching(JLabel component) {
                if (component.getText().toLowerCase().equals(name.toLowerCase())) {
                    return true;
                }
                return false;
            }
        };
        return textMatcher;
    }
}
