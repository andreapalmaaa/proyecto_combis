package pantallas;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportarExcel {

    public void exportarExcel(JTable tabla) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Reporte");

            DefaultTableModel model = (DefaultTableModel) tabla.getModel();

            // Encabezados
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

            // Filas
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    Object valor = model.getValueAt(i, j);
                    cell.setCellValue(valor != null ? valor.toString() : "");
                }
            }
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar reporte en Excel");
            chooser.setFileFilter(new FileNameExtensionFilter("Archivos Excel (*.xlsx)", "xlsx"));

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File archivo = chooser.getSelectedFile();
                String ruta = archivo.getAbsolutePath();

                // Asegurar extensión .xlsx
                if (!ruta.toLowerCase().endsWith(".xlsx")) {
                    ruta += ".xlsx";
                }

                FileOutputStream fileOut = new FileOutputStream(ruta);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

                JOptionPane.showMessageDialog(null, "Reporte exportado en: " + ruta);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage());
        }
    }
}
