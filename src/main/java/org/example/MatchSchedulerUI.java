package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class MatchSchedulerUI extends JFrame {
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private JTextField teamInput;
    private YearMonth currentMonth;
    private Map<Integer, String> scheduledMatches; // Stocke les matchs programmés (jour -> match info)

    public MatchSchedulerUI() {
        setTitle("Gestion des matchs");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        currentMonth = YearMonth.now();
        scheduledMatches = new HashMap<>();

        // ======= Titre =======
        JLabel titleLabel = new JLabel("Gestion des matchs", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // ======= Conteneur principal =======
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // ======= Panneau de création de match =======
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Créer un match automatiquement"));
        leftPanel.setPreferredSize(new Dimension(250, 150));

        leftPanel.add(new JLabel("Équipe opposée :"));
        teamInput = new JTextField(15);
        leftPanel.add(teamInput);

        JButton scheduleButton = new JButton("Calculer un horaire");
        leftPanel.add(scheduleButton);

        scheduleButton.addActionListener(e -> scheduleMatch());

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // ======= Panneau du calendrier =======
        JPanel calendarContainer = new JPanel(new BorderLayout());
        mainPanel.add(calendarContainer, BorderLayout.CENTER);

        // Barre de navigation
        JPanel navPanel = new JPanel();
        JButton prevMonthButton = new JButton("<");
        JButton nextMonthButton = new JButton(">");

        prevMonthButton.addActionListener(e -> changeMonth(-1));
        nextMonthButton.addActionListener(e -> changeMonth(1));

        monthLabel = new JLabel("", SwingConstants.CENTER);
        navPanel.add(prevMonthButton);
        navPanel.add(monthLabel);
        navPanel.add(nextMonthButton);
        calendarContainer.add(navPanel, BorderLayout.NORTH);

        // Panneau du calendrier
        calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarContainer.add(calendarPanel, BorderLayout.CENTER);

        updateCalendar();
    }

    // ======= Met à jour le calendrier avec le mois actuel =======
    private void updateCalendar() {
        calendarPanel.removeAll();
        monthLabel.setText(currentMonth.getMonth().toString() + " " + currentMonth.getYear());

        String[] days = {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        for (String day : days) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            calendarPanel.add(label);
        }

        int firstDayOfMonth = LocalDate.of(currentMonth.getYear(), currentMonth.getMonth(), 1).getDayOfWeek().getValue() % 7;
        int totalDays = currentMonth.lengthOfMonth();

        for (int i = 0; i < firstDayOfMonth; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= totalDays; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            int finalDay = day;

            if (scheduledMatches.containsKey(day)) {
                dayButton.setText(day + " 🏆");
                dayButton.setToolTipText(scheduledMatches.get(day));
            }

            dayButton.addActionListener(e -> addMatchToDay(finalDay));

            calendarPanel.add(dayButton);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    // ======= Ajoute un match à un jour donné =======
    private void addMatchToDay(int day) {
        String team = JOptionPane.showInputDialog("Entrez le nom de l'équipe adverse :");
        if (team != null && !team.trim().isEmpty()) {
            scheduledMatches.put(day, "Match contre " + team + " à 15:00");
            updateCalendar();
        }
    }

    // ======= Planifie un match automatiquement (ex : premier jour disponible) =======
    private void scheduleMatch() {
        String team = teamInput.getText().trim();
        if (team.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom d'équipe.");
            return;
        }

        for (int day = 1; day <= currentMonth.lengthOfMonth(); day++) {
            if (!scheduledMatches.containsKey(day)) {
                scheduledMatches.put(day, "Match contre " + team + " à 15:00");
                JOptionPane.showMessageDialog(this, "Match programmé le " + day + " " + currentMonth.getMonth());
                updateCalendar();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Aucune date disponible ce mois-ci !");
    }

    // ======= Change le mois affiché =======
    private void changeMonth(int offset) {
        currentMonth = currentMonth.plusMonths(offset);
        scheduledMatches.clear(); // Reset pour l'exemple
        updateCalendar();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MatchSchedulerUI().setVisible(true));
    }
}
