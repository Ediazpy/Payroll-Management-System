
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Emmanuel Diaz
 */
public class PayrollGUI extends javax.swing.JFrame
{
    // class references
    DefaultListModel<Employee> employeeList = new DefaultListModel();
    DefaultListModel<String> weeklySalesList = new DefaultListModel();

    private DataIO dataIO;
    private java.util.HashMap<String, Double> dailySales = new java.util.HashMap<>();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PayrollGUI.class.getName());

    /**
     * Creates new form PayrollGUI
     */
    public PayrollGUI()
    {
        initComponents();
        this.setLocationRelativeTo(null);

        // Initialize database and load data
        try
        {
            dataIO = new DataIO();
            loadEmployeesFromDatabase();
            setupWeeklySalesList();
            setupEmployeeListTab();
        }
        catch (Exception ex)
        {
            logger.log(java.util.logging.Level.SEVERE, "Database initialization failed", ex);
            JOptionPane.showMessageDialog(this,
                "Error initializing database: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupEmployeeListTab()
    {
        // Create components for the Employee List tab
        pnlEmployeeList.setLayout(new java.awt.BorderLayout());

        // Create a table to display employees
        String[] columnNames = {"ID", "Name", "Address", "Sales", "Tips", "Parts", "Weekly Pay"};
        javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false; // Make table read-only
            }
        };

        javax.swing.JTable employeeTable = new javax.swing.JTable(tableModel);
        employeeTable.setFont(new java.awt.Font("Segoe UI", 0, 14));
        employeeTable.setRowHeight(25);
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(employeeTable);

        // Create button panel
        javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        javax.swing.JButton btnAddEmployee = new javax.swing.JButton("Add Employee");
        javax.swing.JButton btnEditEmployee = new javax.swing.JButton("Edit Employee");
        javax.swing.JButton btnDeleteEmployee = new javax.swing.JButton("Delete Employee");
        javax.swing.JButton btnRefresh = new javax.swing.JButton("Refresh");

        // Add action listeners
        btnAddEmployee.addActionListener(e -> addEmployeeDialog());
        btnEditEmployee.addActionListener(e -> editEmployeeDialog(employeeTable));
        btnDeleteEmployee.addActionListener(e -> deleteEmployeeDialog(employeeTable));
        btnRefresh.addActionListener(e -> refreshEmployeeTable(tableModel));

        buttonPanel.add(btnAddEmployee);
        buttonPanel.add(btnEditEmployee);
        buttonPanel.add(btnDeleteEmployee);
        buttonPanel.add(btnRefresh);

        pnlEmployeeList.add(scrollPane, java.awt.BorderLayout.CENTER);
        pnlEmployeeList.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        // Initial load of data
        refreshEmployeeTable(tableModel);
    }

    private void refreshEmployeeTable(javax.swing.table.DefaultTableModel tableModel)
    {
        try
        {
            tableModel.setRowCount(0); // Clear existing rows
            java.util.ArrayList<Employee> employees = dataIO.getList();
            java.text.DecimalFormat fmt = new java.text.DecimalFormat("$#,##0.00");

            for (Employee emp : employees)
            {
                Object[] row = {
                    emp.getEmployeeID(),
                    emp.getName(),
                    emp.getAddress(),
                    fmt.format(emp.getSales()),
                    fmt.format(emp.getTips()),
                    fmt.format(emp.getParts()),
                    fmt.format(emp.calculateWeeklySales())
                };
                tableModel.addRow(row);
            }
        }
        catch (Exception ex)
        {
            logger.log(java.util.logging.Level.SEVERE, "Failed to refresh employee table", ex);
            JOptionPane.showMessageDialog(this,
                "Error loading employees: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupWeeklySalesList()
    {
        lstSalesWeek.setModel(weeklySalesList);
        // Initialize all days with $0.00
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days)
        {
            dailySales.put(day, 0.0);
            weeklySalesList.addElement(day + ": $0.00");
        }
    }

    private void loadEmployeesFromDatabase()
    {
        try
        {
            employeeList.clear();
            java.util.ArrayList<Employee> employees = dataIO.getList();
            for (Employee emp : employees)
            {
                employeeList.addElement(emp);
            }
        }
        catch (Exception ex)
        {
            logger.log(java.util.logging.Level.SEVERE, "Failed to load employees", ex);
            JOptionPane.showMessageDialog(this,
                "Error loading employees: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        tabMain = new javax.swing.JTabbedPane();
        pnlInformation = new javax.swing.JPanel();
        lblTitle1 = new javax.swing.JLabel();
        lblInstructions1 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblInstructions2 = new javax.swing.JLabel();
        lblSales = new javax.swing.JLabel();
        txtSales = new javax.swing.JTextField();
        lblParts = new javax.swing.JLabel();
        txtParts = new javax.swing.JTextField();
        lblTips = new javax.swing.JLabel();
        txtTips = new javax.swing.JTextField();
        lblJobTotal = new javax.swing.JLabel();
        btnCalculateJobTotal = new javax.swing.JButton();
        btnLoadToWeek = new javax.swing.JButton();
        jcbDays = new javax.swing.JComboBox<>();
        lblJobTotal1 = new javax.swing.JLabel();
        btnCalculateWeek = new javax.swing.JButton();
        scrSalesWeek = new javax.swing.JScrollPane();
        lstSalesWeek = new javax.swing.JList<>();
        scrJobTotal = new javax.swing.JScrollPane();
        lstJobTotal = new javax.swing.JList<>();
        pnlEmployeeList = new javax.swing.JPanel();
        mnbMain = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mniExit = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mniAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pay Management System");

        lblTitle1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblTitle1.setText("Payroll Management System");

        lblInstructions1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInstructions1.setText("Please enter your information:");

        lblID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblID.setText("ID:");

        txtID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtID.setNextFocusableComponent(txtName);

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblName.setText("Name:");

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtName.setNextFocusableComponent(txtAddress);

        lblAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAddress.setText("Address:");

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAddress.setNextFocusableComponent(txtSales);

        lblInstructions2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInstructions2.setText("Please enter info Individually per customer sale:");

        lblSales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSales.setText("Sales:");

        txtSales.setNextFocusableComponent(txtParts);

        lblParts.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblParts.setText("Parts:");

        txtParts.setNextFocusableComponent(txtTips);

        lblTips.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTips.setText("Tips:");

        lblJobTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblJobTotal.setText("Job Total");

        btnCalculateJobTotal.setText("Calculate Job");
        btnCalculateJobTotal.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCalculateJobTotalActionPerformed(evt);
            }
        });

        btnLoadToWeek.setText("Load Total");
        btnLoadToWeek.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLoadToWeekActionPerformed(evt);
            }
        });

        jcbDays.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));

        lblJobTotal1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblJobTotal1.setText("Sales for the week");

        btnCalculateWeek.setText("Calculate Week");
        btnCalculateWeek.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCalculateWeekActionPerformed(evt);
            }
        });

        lstSalesWeek.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        scrSalesWeek.setViewportView(lstSalesWeek);

        lstJobTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lstJobTotal.setModel(employeeList);
        scrJobTotal.setViewportView(lstJobTotal);

        javax.swing.GroupLayout pnlInformationLayout = new javax.swing.GroupLayout(pnlInformation);
        pnlInformation.setLayout(pnlInformationLayout);
        pnlInformationLayout.setHorizontalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addComponent(lblInstructions1)
                        .addGap(67, 67, 67)
                        .addComponent(lblJobTotal)
                        .addGap(106, 106, 106)
                        .addComponent(lblJobTotal1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInformationLayout.createSequentialGroup()
                                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInformationLayout.createSequentialGroup()
                                                .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtAddress))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInformationLayout.createSequentialGroup()
                                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(144, 144, 144)
                                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrJobTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlInformationLayout.createSequentialGroup()
                                        .addComponent(jcbDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLoadToWeek)))
                                .addGap(6, 6, 6)
                                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrSalesWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlInformationLayout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(btnCalculateWeek))))
                            .addGroup(pnlInformationLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSales, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTips, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                        .addComponent(txtParts, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(pnlInformationLayout.createSequentialGroup()
                                .addComponent(lblTips, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCalculateJobTotal))
                            .addComponent(lblParts, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInstructions2))
                        .addContainerGap(244, Short.MAX_VALUE))))
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(lblTitle1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInformationLayout.setVerticalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitle1)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInstructions1)
                            .addComponent(lblJobTotal)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformationLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJobTotal1)))
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblID)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAddress)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrSalesWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrJobTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCalculateWeek)
                    .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLoadToWeek)
                        .addComponent(jcbDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(lblInstructions2)
                .addGap(24, 24, 24)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSales)
                    .addComponent(txtSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtParts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblParts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTips)
                    .addComponent(txtTips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCalculateJobTotal)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tabMain.addTab("Information", pnlInformation);

        javax.swing.GroupLayout pnlEmployeeListLayout = new javax.swing.GroupLayout(pnlEmployeeList);
        pnlEmployeeList.setLayout(pnlEmployeeListLayout);
        pnlEmployeeListLayout.setHorizontalGroup(
            pnlEmployeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlEmployeeListLayout.setVerticalGroup(
            pnlEmployeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabMain.addTab("Employee List", pnlEmployeeList);

        mnuFile.setText("File");

        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mniExitActionPerformed(evt);
            }
        });
        mnuFile.add(mniExit);

        mnbMain.add(mnuFile);

        mnuHelp.setText("Help");

        mniAbout.setText("About");
        mniAbout.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mniAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mniAbout);

        mnbMain.add(mnuHelp);

        setJMenuBar(mnbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabMain)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabMain)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mniExitActionPerformed
    {//GEN-HEADEREND:event_mniExitActionPerformed
        // Exit the program
        System.exit(0);
    }//GEN-LAST:event_mniExitActionPerformed

    private void mniAboutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mniAboutActionPerformed
    {//GEN-HEADEREND:event_mniAboutActionPerformed
        String output = "Author: Emmanuel Diaz";
        output += "\nEmail: emmanueldiaz24@gmail.com ";
        JOptionPane.showMessageDialog(this, output);
    }//GEN-LAST:event_mniAboutActionPerformed

    private void btnCalculateJobTotalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCalculateJobTotalActionPerformed
    {//GEN-HEADEREND:event_btnCalculateJobTotalActionPerformed
        // Validate input fields
        String salesStr = txtSales.getText().trim();
        String partsStr = txtParts.getText().trim();
        String tipsStr = txtTips.getText().trim();

        if (salesStr.isEmpty() || partsStr.isEmpty() || tipsStr.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                "Please enter Sales, Parts, and Tips values",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Employee.isValidNumber(salesStr) || !Employee.isValidNumber(partsStr) || !Employee.isValidNumber(tipsStr))
        {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numeric values for Sales, Parts, and Tips",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculate job total
        double sales = Double.parseDouble(salesStr);
        double parts = Double.parseDouble(partsStr);
        double tips = Double.parseDouble(tipsStr);

        // Commission calculation: 50% of (sales minus parts) plus tips
        double jobTotal = ((sales - parts) / 2.0) + tips;
        jobTotal = Math.max(jobTotal, 0.0); // Ensure non-negative

        // Display in the job total list
        java.text.DecimalFormat fmt = new java.text.DecimalFormat("$#,##0.00");
        String jobInfo = "Sales: " + fmt.format(sales) +
                         " | Parts: " + fmt.format(parts) +
                         " | Tips: " + fmt.format(tips) +
                         " | Total: " + fmt.format(jobTotal);

        employeeList.addElement(new Employee(0, jobInfo, "", sales, tips, parts));

        // Clear the input fields for next entry
        txtSales.setText("");
        txtParts.setText("");
        txtTips.setText("");
        txtSales.requestFocus();

        JOptionPane.showMessageDialog(this,
            "Job Total: " + fmt.format(jobTotal),
            "Calculation Complete",
            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnCalculateJobTotalActionPerformed

    private void btnLoadToWeekActionPerformed(java.awt.event.ActionEvent evt)
    {
        // Get the last calculated job total from the job total list
        if (employeeList.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                "Please calculate a job total first",
                "No Job Total",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the selected day
        String selectedDay = (String) jcbDays.getSelectedItem();

        // Get the last employee (job) from the list
        Employee lastJob = employeeList.getElementAt(employeeList.getSize() - 1);
        double jobTotal = lastJob.calculateWeeklySales();

        // Update the daily sales map
        double currentDayTotal = dailySales.get(selectedDay);
        currentDayTotal += jobTotal;
        dailySales.put(selectedDay, currentDayTotal);

        // Update the weekly sales list display
        updateWeeklySalesDisplay();

        java.text.DecimalFormat fmt = new java.text.DecimalFormat("$#,##0.00");
        JOptionPane.showMessageDialog(this,
            "Added " + fmt.format(jobTotal) + " to " + selectedDay,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnCalculateWeekActionPerformed(java.awt.event.ActionEvent evt)
    {
        // Calculate total for the week
        double weeklyTotal = 0.0;
        for (double dayTotal : dailySales.values())
        {
            weeklyTotal += dayTotal;
        }

        java.text.DecimalFormat fmt = new java.text.DecimalFormat("$#,##0.00");
        String message = "Total Weekly Earnings: " + fmt.format(weeklyTotal) + "\n\n";
        message += "Breakdown by day:\n";

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days)
        {
            message += day + ": " + fmt.format(dailySales.get(day)) + "\n";
        }

        JOptionPane.showMessageDialog(this,
            message,
            "Weekly Total",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateWeeklySalesDisplay()
    {
        weeklySalesList.clear();
        java.text.DecimalFormat fmt = new java.text.DecimalFormat("$#,##0.00");

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days)
        {
            double total = dailySales.get(day);
            weeklySalesList.addElement(day + ": " + fmt.format(total));
        }
    }

    private void addEmployeeDialog()
    {
        // Create input panel
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(6, 2, 5, 5));

        javax.swing.JTextField txtEmpID = new javax.swing.JTextField();
        javax.swing.JTextField txtEmpName = new javax.swing.JTextField();
        javax.swing.JTextField txtEmpAddress = new javax.swing.JTextField();
        javax.swing.JTextField txtEmpSales = new javax.swing.JTextField("0.0");
        javax.swing.JTextField txtEmpTips = new javax.swing.JTextField("0.0");
        javax.swing.JTextField txtEmpParts = new javax.swing.JTextField("0.0");

        panel.add(new javax.swing.JLabel("Employee ID:"));
        panel.add(txtEmpID);
        panel.add(new javax.swing.JLabel("Name:"));
        panel.add(txtEmpName);
        panel.add(new javax.swing.JLabel("Address:"));
        panel.add(txtEmpAddress);
        panel.add(new javax.swing.JLabel("Sales:"));
        panel.add(txtEmpSales);
        panel.add(new javax.swing.JLabel("Tips:"));
        panel.add(txtEmpTips);
        panel.add(new javax.swing.JLabel("Parts:"));
        panel.add(txtEmpParts);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Employee",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION)
        {
            try
            {
                // Validate inputs
                String idStr = txtEmpID.getText().trim();
                String name = txtEmpName.getText().trim();
                String address = txtEmpAddress.getText().trim();

                if (idStr.isEmpty() || name.isEmpty() || address.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,
                        "Please fill in all required fields (ID, Name, Address)",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!Employee.isValidID(idStr))
                {
                    JOptionPane.showMessageDialog(this,
                        "Please enter a valid Employee ID (positive integer)",
                        "Invalid ID",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int empId = Integer.parseInt(idStr);

                // Check if employee already exists
                if (dataIO.exists(empId))
                {
                    JOptionPane.showMessageDialog(this,
                        "Employee ID " + empId + " already exists",
                        "Duplicate ID",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!Employee.isValidNumber(txtEmpSales.getText()) ||
                    !Employee.isValidNumber(txtEmpTips.getText()) ||
                    !Employee.isValidNumber(txtEmpParts.getText()))
                {
                    JOptionPane.showMessageDialog(this,
                        "Please enter valid numeric values for Sales, Tips, and Parts",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double sales = Double.parseDouble(txtEmpSales.getText().trim());
                double tips = Double.parseDouble(txtEmpTips.getText().trim());
                double parts = Double.parseDouble(txtEmpParts.getText().trim());

                // Create and save employee
                Employee emp = new Employee(empId, name, address, sales, tips, parts);
                dataIO.add(emp);

                JOptionPane.showMessageDialog(this,
                    "Employee added successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

                loadEmployeesFromDatabase();
            }
            catch (Exception ex)
            {
                logger.log(java.util.logging.Level.SEVERE, "Failed to add employee", ex);
                JOptionPane.showMessageDialog(this,
                    "Error adding employee: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editEmployeeDialog(javax.swing.JTable table)
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(this,
                "Please select an employee to edit",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            int empId = (Integer) table.getValueAt(selectedRow, 0);
            Employee emp = dataIO.getById(empId);

            if (emp == null)
            {
                JOptionPane.showMessageDialog(this,
                    "Employee not found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create input panel
            javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(6, 2, 5, 5));

            javax.swing.JTextField txtEmpID = new javax.swing.JTextField(String.valueOf(emp.getEmployeeID()));
            txtEmpID.setEditable(false); // Don't allow changing ID
            javax.swing.JTextField txtEmpName = new javax.swing.JTextField(emp.getName());
            javax.swing.JTextField txtEmpAddress = new javax.swing.JTextField(emp.getAddress());
            javax.swing.JTextField txtEmpSales = new javax.swing.JTextField(String.valueOf(emp.getSales()));
            javax.swing.JTextField txtEmpTips = new javax.swing.JTextField(String.valueOf(emp.getTips()));
            javax.swing.JTextField txtEmpParts = new javax.swing.JTextField(String.valueOf(emp.getParts()));

            panel.add(new javax.swing.JLabel("Employee ID:"));
            panel.add(txtEmpID);
            panel.add(new javax.swing.JLabel("Name:"));
            panel.add(txtEmpName);
            panel.add(new javax.swing.JLabel("Address:"));
            panel.add(txtEmpAddress);
            panel.add(new javax.swing.JLabel("Sales:"));
            panel.add(txtEmpSales);
            panel.add(new javax.swing.JLabel("Tips:"));
            panel.add(txtEmpTips);
            panel.add(new javax.swing.JLabel("Parts:"));
            panel.add(txtEmpParts);

            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Employee",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION)
            {
                String name = txtEmpName.getText().trim();
                String address = txtEmpAddress.getText().trim();

                if (name.isEmpty() || address.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,
                        "Name and Address cannot be empty",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!Employee.isValidNumber(txtEmpSales.getText()) ||
                    !Employee.isValidNumber(txtEmpTips.getText()) ||
                    !Employee.isValidNumber(txtEmpParts.getText()))
                {
                    JOptionPane.showMessageDialog(this,
                        "Please enter valid numeric values for Sales, Tips, and Parts",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double sales = Double.parseDouble(txtEmpSales.getText().trim());
                double tips = Double.parseDouble(txtEmpTips.getText().trim());
                double parts = Double.parseDouble(txtEmpParts.getText().trim());

                // Update employee
                emp.setName(name);
                emp.setAddress(address);
                emp.setSales(sales);
                emp.setTips(tips);
                emp.setParts(parts);

                dataIO.update(emp);

                JOptionPane.showMessageDialog(this,
                    "Employee updated successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

                loadEmployeesFromDatabase();
            }
        }
        catch (Exception ex)
        {
            logger.log(java.util.logging.Level.SEVERE, "Failed to edit employee", ex);
            JOptionPane.showMessageDialog(this,
                "Error updating employee: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployeeDialog(javax.swing.JTable table)
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(this,
                "Please select an employee to delete",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            int empId = (Integer) table.getValueAt(selectedRow, 0);
            String empName = (String) table.getValueAt(selectedRow, 1);

            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete employee:\n" +
                "ID: " + empId + "\n" +
                "Name: " + empName + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION)
            {
                boolean deleted = dataIO.delete(empId);

                if (deleted)
                {
                    JOptionPane.showMessageDialog(this,
                        "Employee deleted successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    loadEmployeesFromDatabase();
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                        "Failed to delete employee",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch (Exception ex)
        {
            logger.log(java.util.logging.Level.SEVERE, "Failed to delete employee", ex);
            JOptionPane.showMessageDialog(this,
                "Error deleting employee: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PayrollGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculateJobTotal;
    private javax.swing.JButton btnCalculateWeek;
    private javax.swing.JButton btnLoadToWeek;
    private javax.swing.JComboBox<String> jcbDays;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblInstructions1;
    private javax.swing.JLabel lblInstructions2;
    private javax.swing.JLabel lblJobTotal;
    private javax.swing.JLabel lblJobTotal1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblParts;
    private javax.swing.JLabel lblSales;
    private javax.swing.JLabel lblTips;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JList<Employee> lstJobTotal;
    private javax.swing.JList<String> lstSalesWeek;
    private javax.swing.JMenuBar mnbMain;
    private javax.swing.JMenuItem mniAbout;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JPanel pnlEmployeeList;
    private javax.swing.JPanel pnlInformation;
    private javax.swing.JScrollPane scrJobTotal;
    private javax.swing.JScrollPane scrSalesWeek;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtParts;
    private javax.swing.JTextField txtSales;
    private javax.swing.JTextField txtTips;
    // End of variables declaration//GEN-END:variables
}
