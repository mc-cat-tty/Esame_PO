package Listeners;

import DatePickerGUI.MyDatePicker;
import TableModel.Invoice;
import TableModel.InvoicesTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class editListener implements ActionListener {
    private InvoicesTableModel model;
    private JTable table;
    public editListener(InvoicesTableModel model, JTable table) {
        this.model=model;
        this.table=table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index=table.getSelectedRow();
        MyDatePicker addDatePicker= new MyDatePicker();
        addDatePicker.setDate((LocalDate) model.getValueAt(index,0));
        JTextField amountField = new JTextField();
        amountField.setText(String.valueOf(model.getValueAt(index,1)));
        JTextArea descriptionField= new JTextArea((String) model.getValueAt(index,2),5,20);
        descriptionField.setFont(new Font("corsivo",Font.ITALIC,descriptionField.getFont().getSize()));

        final JComponent[] inputsComponent = new JComponent[] {
                new JLabel("Date:"),
                addDatePicker,
                new JLabel("Amount"),
                amountField,
                new JLabel("Brief description:"),
                descriptionField
        };
        int editPanelExitStatus = JOptionPane.showConfirmDialog(table, inputsComponent, "Edit the invoice", JOptionPane.DEFAULT_OPTION);
        if (editPanelExitStatus == JOptionPane.OK_OPTION) {
            model.setValueAt(addDatePicker.getDate(),index,0);
            model.setValueAt(Double.parseDouble((amountField.getText())),index,1);
            model.setValueAt(descriptionField.getText(),index,2);

        } else {
            System.out.println("User canceled/closed the dialog, exit status = " + editPanelExitStatus);
        }

    }
}
