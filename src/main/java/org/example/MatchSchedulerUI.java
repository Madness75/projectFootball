package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class MatchSchedulerUI extends JFrame {
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private JTextField teamInput;
    private JTextField matchTimeInput;
    private YearMonth currentMonth;
    private static String currentUserEmail; // Stocke l'email de l'utilisateur actuel
    private static Map<YearMonth, Map<Integer, Map<String, String>>> allScheduledMatches; // Stocke les matchs de tous les mois

    // Constructeur
    public MatchSchedulerUI(String userEmail) {
        currentUserEmail = userEmail; // Récupère l'email de l'utilisateur connecté
        setTitle("Gestion des matchs");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        currentMonth = YearMonth.now();
        if (allScheduledMatches == null) {
            allScheduledMatches = new HashMap<>();
        }

        // Titre de la fenêtre
        JLabel titleLabel = new JLabel("Gestion des matchs", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Conteneur principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Panneau de création de match
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Créer un match"));
        leftPanel.setPreferredSize(new Dimension(250, 150));

        leftPanel.add(new JLabel("Équipe opposée :"));
        teamInput = new JTextField(15);
        leftPanel.add(teamInput);

        leftPanel.add(new JLabel("Horaire du match (format HH:mm) :"));
        matchTimeInput = new JTextField(5);
        leftPanel.add(matchTimeInput);

        JButton scheduleButton = new JButton("Planifier le match");
        leftPanel.add(scheduleButton);

        scheduleButton.addActionListener(e -> scheduleMatch());

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Panneau du calendrier
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

    // Met à jour le calendrier avec le mois actuel
    private void updateCalendar() {
        calendarPanel.removeAll();
        monthLabel.setText(currentMonth.getMonth().toString() + " " + currentMonth.getYear());

        String[] days = {"Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"};
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

        // Vérifier si des matchs ont été programmés pour ce mois
        Map<Integer, Map<String, String>> currentMonthMatches = allScheduledMatches.get(currentMonth);
        if (currentMonthMatches == null) {
            currentMonthMatches = new HashMap<>();
            allScheduledMatches.put(currentMonth, currentMonthMatches);
        }

        for (int day = 1; day <= totalDays; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            int finalDay = day;

            // Afficher les informations sur les matchs déjà programmés
            if (currentMonthMatches.containsKey(day)) {
                StringBuilder matchInfo = new StringBuilder();
                Map<String, String> matchesForDay = currentMonthMatches.get(day);
                for (String time : matchesForDay.keySet()) {
                    matchInfo.append("Match contre ").append(matchesForDay.get(time)).append(" à ").append(time).append("\n");
                }
                dayButton.setText(day + " 🏆");
                dayButton.setToolTipText(matchInfo.toString()); // Affiche les matchs de la journée
            }

            dayButton.addActionListener(e -> addMatchToDay(finalDay));

            calendarPanel.add(dayButton);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    // Ajoute un match à un jour donné
    private void addMatchToDay(int day) {
        String team = JOptionPane.showInputDialog("Entrez le nom de l'équipe adverse :");
        String matchTime = JOptionPane.showInputDialog("Entrez l'horaire du match (format HH:mm) :");

        if (team != null && !team.trim().isEmpty() && matchTime != null && !matchTime.trim().isEmpty()) {
            // Vérifie si le créneau horaire est déjà pris pour ce jour
            Map<Integer, Map<String, String>> currentMonthMatches = allScheduledMatches.get(currentMonth);
            if (currentMonthMatches.containsKey(day) && currentMonthMatches.get(day).containsKey(matchTime)) {
                JOptionPane.showMessageDialog(this, "Ce créneau horaire est déjà réservé. Choisissez un autre horaire.");
            } else {
                // Ajoute le match au jour et à l'horaire
                currentMonthMatches.computeIfAbsent(day, k -> new HashMap<>()).put(matchTime, team);
                updateCalendar();
            }
        }
    }

    // Planifie un match automatiquement (ex : premier jour disponible)
    private void scheduleMatch() {
        String team = teamInput.getText().trim();
        String matchTime = matchTimeInput.getText().trim();

        if (team.isEmpty() || matchTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom d'équipe et un horaire.");
            return;
        }

        Map<Integer, Map<String, String>> currentMonthMatches = allScheduledMatches.get(currentMonth);
        if (currentMonthMatches == null) {
            currentMonthMatches = new HashMap<>();
            allScheduledMatches.put(currentMonth, currentMonthMatches);
        }

        for (int day = 1; day <= currentMonth.lengthOfMonth(); day++) {
            if (!currentMonthMatches.containsKey(day) || !currentMonthMatches.get(day).containsKey(matchTime)) {
                // Ajoute le match pour le premier jour et l'horaire disponible
                currentMonthMatches.computeIfAbsent(day, k -> new HashMap<>()).put(matchTime, team);
                JOptionPane.showMessageDialog(this, "Match programmé le " + day + " " + currentMonth.getMonth() + " à " + matchTime);
                updateCalendar();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Aucune date disponible ce mois-ci !");
    }

    // Change le mois affiché
    private void changeMonth(int offset) {
        currentMonth = currentMonth.plusMonths(offset);
        updateCalendar();
    }

    public static void main(String[] args) {
        // Utilisez une adresse e-mail d'utilisateur simulée pour ce test
        String userEmail = "user@example.com"; // Cette adresse peut être récupérée après l'inscription ou la connexion
        SwingUtilities.invokeLater(() -> new MatchSchedulerUI(userEmail).setVisible(true));
    }
}
