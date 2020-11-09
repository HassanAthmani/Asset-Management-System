package asset_management_system.reports.reportCreator;

import asset_management_system.dashboard.DashboardController;
import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.awt.DefaultFontMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import org.json.simple.parser.ParseException;
import java.util.Date;

public class creator {

    notification notify = new notification();
    List<String> A = new ArrayList<String>();
    PreparedStatement ps;
    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    String dbName = "asset_management_system";
    String userName = "root";
    String password = "";

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();

    public JFreeChart piechart(String table, String title, String from, String to, String column) throws SQLException {

        DefaultPieDataset dataSet = new DefaultPieDataset();
        A.add("LAPTOP");
        A.add("PROJECTOR");
        A.add("CAMERA");
        A.add("MOBILE PHONE");
        A.add("PRINTER");
        A.add("KITCHEN ITEM");
        A.add("MACHINERY ITEM");

        for (int i = 0; i < A.size(); i++) {

            try {
                String dbName = "asset_management_system";
                String userName = "root";
                String password = "";
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

                ps = connection.prepareStatement("SELECT COUNT(*) FROM " + table + " WHERE categoryID='" + A.get(i) + "'AND " + column + " BETWEEN '" + from + "' AND '" + to + "'");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    dataSet.setValue(A.get(i), rs.getInt(1));

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        JFreeChart chart = ChartFactory.createPieChart(
                title, dataSet, true, true, true);
        return chart;
    }

    public JFreeChart barchart(String table, String title) throws SQLException {

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        A.add("LAPTOP");
        A.add("PROJECTOR");
        A.add("CAMERA");
        A.add("MOBILE PHONE");
        A.add("PRINTER");
        A.add("KITCHEN ITEM");
        A.add("MACHINERY ITEM");

        for (int i = 0; i < A.size(); i++) {

            try {
                String dbName = "asset_management_system";
                String userName = "root";
                String password = "";
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

                ps = connection.prepareStatement("SELECT SUM(total_cost) FROM " + table + " WHERE categoryID='" + A.get(i) + "'");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    dataSet.setValue(rs.getInt(1), "Category", A.get(i));

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        JFreeChart chart = ChartFactory.createBarChart(
                title, "CATEGORY", "COST",
                dataSet, PlotOrientation.VERTICAL, false, true, false);

        return chart;
    }

    //ALL ASSETS REPORT
    public void all_assets(String from, String to, Button button) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException, IOException, ParseException {
        Class.forName("com.mysql.jdbc.Driver");

        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/ASSET_REPORT" + from + "_" + to + ".pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(9);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET DETAILS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("CATEGORY ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ADDITION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("COST", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  ALL ASSETS TABLE ");
        Paragraph report_dit = new Paragraph("ASSETS REPORT FROM " + from + " to " + to);
        Paragraph today_date = new Paragraph("REPORT CREATION DATE: " + String.valueOf(formatter.format(date)));
        my_pdf_report.add(report_dit);
        my_pdf_report.add(today_date);
        my_pdf_report.add(space);
        my_pdf_report.add(title/*.setIndentationLeft(indent)*/);
        my_pdf_report.add(space);
        my_pdf_report.add(tableName);

        try {
            json_read jsonReader = new json_read();
            String id = jsonReader.profile_id();

            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details WHERE workerID= " + id);

            while (rs.next()) {

                Paragraph workerid = new Paragraph("WORKER ID: " + rs.getString(1));
                Paragraph workername = new Paragraph("WORKER NAME: " + rs.getString(2));
                Paragraph email = new Paragraph("WORKER EMAIL: " + rs.getString(5));
                Paragraph department = new Paragraph("WORKER DEPARTMENT: " + rs.getString(7));

                my_pdf_report.add(workerid);
                my_pdf_report.add(workername);
                my_pdf_report.add(email);
                my_pdf_report.add(department);
                my_pdf_report.add(space);
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
        ps = connection.prepareStatement("SELECT * FROM assets WHERE additionDate BETWEEN '" + from + "' AND '" + to + "'");
        ResultSet rs = ps.executeQuery();

        if (rs.next() == false) {

            notify.flash(button, " NO RECORDS ENOUGH TO CREATE A REPORT ");

        } else {

            while (rs.next()) {
                // System.err.println(columnA.getCellData(o));
                String id = rs.getString(1);
                table_cell = new PdfPCell(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String name = rs.getString(2);
                table_cell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String code = rs.getString(3);
                table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String details = rs.getString(4);
                table_cell = new PdfPCell(new Phrase(details, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerName = rs.getString(5);
                table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerID = rs.getString(6);
                table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String catID = rs.getString(7);
                table_cell = new PdfPCell(new Phrase(catID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String addDate = rs.getString(8);
                table_cell = new PdfPCell(new Phrase(addDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String cost0 = rs.getString(9);
                table_cell = new PdfPCell(new Phrase(cost0, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

            }
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();

            notify.flash(button, " REPORT HAS BEEN CREATED ");
        }

    }

    //ASSET IN MAINTENANCE
    public void assets_maintenance(String from, String to, Button button) throws FileNotFoundException, DocumentException, IOException, ParseException, SQLException {
        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/MAINTENANCE_REPORT_" + from + "_" + to + ".pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(9);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET DETAILS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("CATEGORY ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ADDITION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("MAINTENANCE DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  MAINTENANCE TABLE ");
        my_pdf_report.add(title/*.setIndentationLeft(indent)*/);
        my_pdf_report.add(space);
        my_pdf_report.add(tableName);

        try {
            json_read jsonReader = new json_read();
            String id = jsonReader.profile_id();

            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details WHERE workerID= " + id);

            while (rs.next()) {

                Paragraph workerid = new Paragraph("WORKER ID: " + rs.getString(1));
                Paragraph workername = new Paragraph("WORKER NAME: " + rs.getString(2));
                Paragraph email = new Paragraph("WORKER EMAIL: " + rs.getString(5));
                Paragraph department = new Paragraph("WORKER DEPARTMENT: " + rs.getString(7));
                Paragraph report_dit = new Paragraph("ASSETS IN MAINTENANCE REPORT FROM " + from + " to " + to);
                Paragraph today_date = new Paragraph("REPORT CREATION DATE: " + String.valueOf(formatter.format(date)));
                my_pdf_report.add(report_dit);
                my_pdf_report.add(today_date);
                my_pdf_report.add(space);

                my_pdf_report.add(workerid);
                my_pdf_report.add(workername);
                my_pdf_report.add(email);
                my_pdf_report.add(department);
                my_pdf_report.add(space);
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
        ps = connection.prepareStatement("SELECT * FROM asset_maintenance WHERE maintenanceDate BETWEEN '" + from + "' AND '" + to + "'");
        ResultSet rs = ps.executeQuery();

        if (rs.next() == false) {

            notify.flash(button, " NO RECORDS ENOUGH TO CREATE A REPORT ");

        } else {

            while (rs.next()) {

                String id = rs.getString(1);
                table_cell = new PdfPCell(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String name = rs.getString(2);
                table_cell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String code = rs.getString(3);
                table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String details = rs.getString(4);
                table_cell = new PdfPCell(new Phrase(details, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerName = rs.getString(5);
                table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerID = rs.getString(6);
                table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String catID = rs.getString(7);
                table_cell = new PdfPCell(new Phrase(catID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String addDate = rs.getString(8);
                table_cell = new PdfPCell(new Phrase(addDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String cost0 = rs.getString(9);
                table_cell = new PdfPCell(new Phrase(cost0, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            notify.flash(button, " REPORT HAS BEEN CREATED ");
        }
        /* Attach report table to PDF */
       /* my_pdf_report.add(my_report_table);
        my_pdf_report.close();
        notify.flash(button, " REPORT HAS BEEN CREATED ");*/
    }

    //DEFERRED ASSETS
    public void deferred_assets(String from, String to, Button button) throws FileNotFoundException, DocumentException, IOException, ParseException, SQLException {
        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/DEFERRED_REPORT_" + from + "_" + to + ".pdf"));
        my_pdf_report.open();

        PdfPTable my_report_table = new PdfPTable(10);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET DETAILS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("CATEGORY ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ADDITION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("DEFERRED DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("REASON", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  DEFERRED ASSETS TABLE ");
        my_pdf_report.add(title/*.setIndentationLeft(indent)*/);
        my_pdf_report.add(space);
        my_pdf_report.add(tableName);

        try {
            json_read jsonReader = new json_read();
            String id = jsonReader.profile_id();

            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details WHERE workerID= " + id);

            while (rs.next()) {

                Paragraph workerid = new Paragraph("WORKER ID: " + rs.getString(1));
                Paragraph workername = new Paragraph("WORKER NAME: " + rs.getString(2));
                Paragraph email = new Paragraph("WORKER EMAIL: " + rs.getString(5));
                Paragraph department = new Paragraph("WORKER DEPARTMENT: " + rs.getString(7));
                Paragraph report_dit = new Paragraph("DEFERRED ASSETS REPORT FROM " + from + " to " + to);
                Paragraph today_date = new Paragraph("REPORT CREATION DATE: " + String.valueOf(formatter.format(date)));
                my_pdf_report.add(report_dit);
                my_pdf_report.add(today_date);
                my_pdf_report.add(space);

                my_pdf_report.add(workerid);
                my_pdf_report.add(workername);
                my_pdf_report.add(email);
                my_pdf_report.add(department);
                my_pdf_report.add(space);
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
        ps = connection.prepareStatement("SELECT * FROM deferred_asset WHERE deferredDate BETWEEN '" + from + "' AND '" + to + "'");
        ResultSet rs = ps.executeQuery();

        if (rs.next() == false) {

            notify.flash(button, " NO RECORDS ENOUGH TO CREATE A REPORT ");

        } else {

            while (rs.next()) {

                String id = rs.getString(1);
                table_cell = new PdfPCell(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String name = rs.getString(2);
                table_cell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String code = rs.getString(3);
                table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String details = rs.getString(4);
                table_cell = new PdfPCell(new Phrase(details, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerName = rs.getString(5);
                table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerID = rs.getString(6);
                table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String catID = rs.getString(7);
                table_cell = new PdfPCell(new Phrase(catID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String addDate = rs.getString(8);
                table_cell = new PdfPCell(new Phrase(addDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String deferredDate = rs.getString(9);
                table_cell = new PdfPCell(new Phrase(deferredDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String reason = rs.getString(10);
                table_cell = new PdfPCell(new Phrase(reason, FontFactory.getFont(FontFactory.HELVETICA, 7)));
                my_report_table.addCell(table_cell);

            }
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();

            notify.flash(button, " REPORT HAS BEEN CREATED ");
        }

    }

    //ASSGINED ASSTES
    public void assigned_assets(String from, String to, Button button) throws SQLException, DocumentException, FileNotFoundException, IOException, ParseException {

        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/ASSIGNED ASSETS REPORT_" + from + "_" + to + ".pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(10);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER TEL.", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER EMAIL", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSIGNED DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSIGNED BY", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  ASSIGNED ASSET TABLE ");
        my_pdf_report.add(title/*.setIndentationLeft(indent)*/);
        my_pdf_report.add(space);
        my_pdf_report.add(tableName);

        try {
            json_read jsonReader = new json_read();
            String id = jsonReader.profile_id();

            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details WHERE workerID= " + id);

            while (rs.next()) {

                Paragraph workerid = new Paragraph("WORKER ID: " + rs.getString(1));
                Paragraph workername = new Paragraph("WORKER NAME: " + rs.getString(2));
                Paragraph email = new Paragraph("WORKER EMAIL: " + rs.getString(5));
                Paragraph department = new Paragraph("WORKER DEPARTMENT: " + rs.getString(7));
                Paragraph report_dit = new Paragraph("ASSIGNED ASSETS REPORT FROM " + from + " to " + to);
                Paragraph today_date = new Paragraph("REPORT CREATION DATE: " + String.valueOf(formatter.format(date)));
                my_pdf_report.add(report_dit);
                my_pdf_report.add(today_date);
                my_pdf_report.add(space);

                my_pdf_report.add(workerid);
                my_pdf_report.add(workername);
                my_pdf_report.add(email);
                my_pdf_report.add(department);
                my_pdf_report.add(space);
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
        ps = connection.prepareStatement("SELECT * FROM assigned_asset WHERE assigned_date BETWEEN '" + from + "' AND '" + to + "'");
        ResultSet rs = ps.executeQuery();

        if (rs.next() == false) {

            notify.flash(button, " NO RECORDS ENOUGH TO CREATE A REPORT ");

        } else {

            while (rs.next()) {
                String Transid = rs.getString(1);
                table_cell = new PdfPCell(new Phrase(Transid, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerID = rs.getString(2);
                table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerName = rs.getString(3);
                table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerPhoneNo = rs.getString(4);
                table_cell = new PdfPCell(new Phrase(workerPhoneNo, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String workerEmail = rs.getString(5);
                table_cell = new PdfPCell(new Phrase(workerEmail, FontFactory.getFont(FontFactory.HELVETICA, 7)));
                my_report_table.addCell(table_cell);

                String assetID = rs.getString(6);
                table_cell = new PdfPCell(new Phrase(assetID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String assetName = rs.getString(7);
                table_cell = new PdfPCell(new Phrase(assetName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String code = rs.getString(8);
                table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String assignedDate = rs.getString(9);
                table_cell = new PdfPCell(new Phrase(assignedDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

                String assigned_by = rs.getString(10);
                table_cell = new PdfPCell(new Phrase(assigned_by, FontFactory.getFont(FontFactory.HELVETICA, 8)));
                my_report_table.addCell(table_cell);

            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();

            //notification notify = new notification();
            notify.flash(button, " REPORT HAS BEEN CREATED ");

        }

    }

}
