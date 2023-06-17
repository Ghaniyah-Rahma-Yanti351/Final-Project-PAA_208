/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paaproject extends JFrame {

    private static final int CELL_SIZE = 30;
    private static final int MAP_SIZE = 15;
    private static final int WINDOW_SIZE = CELL_SIZE * MAP_SIZE;
    private int redDroidX = 2;
    private int redDroidY = 2;
    private int greenDroidX = 12;
    private int greenDroidY = 12;
    private boolean isStarted = false; // Menandakan apakah permainan sudah dimulai
    private final Timer timer;
    private PopupMenu pauseButton;
    private boolean[][] walls;

    public Paaproject() {
        setTitle("Map Peta");
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tambahkan JButton "Start"
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarted = true; // Set isStarted menjadi true saat tombol "Start" ditekan
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Perbarui posisi droid merah dan droid hijau secara acak di jalan
                        randomizeRedDroidPosition();
                        randomizeGreenDroidPosition();
                        repaint();
                        
                    }

                    private void randomizeRedDroidPosition() {
                        redDroidX = (int) (Math.random() * (MAP_SIZE - 2)) + 1;
                        redDroidY = (int) (Math.random() * (MAP_SIZE - 2)) + 1;

                    }

                    private void randomizeGreenDroidPosition() {
                        greenDroidX = (int) (Math.random() * (MAP_SIZE - 2)) + 1;
                        greenDroidY = (int) (Math.random() * (MAP_SIZE - 2)) + 1;
                    }
                });
                timer.start(); // Memulai timer untuk memperbarui posisi droid setiap 2 detik
            }
        });
        
        

        // Tambahkan JButton "Pause"
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarted = false; // Set isStarted menjadi false saat tombol "Pause" ditekan
            }
        });

        // Tambahkan JButton "Acak Tembok"
        JButton randomizeButton = new JButton("Acak Tembok");
        randomizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomizeWalls(); // Mengacak posisi tembok
                repaint(); // Gambar ulang frame dengan pola tembok yang baru
            }
        });

        // Tambahkan JButton "Acak Posisi Droid Merah"
        JButton randomizeRedDroidButton = new JButton("Acak Posisi Droid Merah");
        randomizeRedDroidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomizeRedDroidPosition(); // Mengacak posisi droid merah di jalan
                repaint(); // Gambar ulang frame
            }
        });
        
         // Tambahkan JButton "Acak Posisi Droid Hijau"
        JButton randomizeGreenDroidButton = new JButton("Acak Posisi Droid Hijau");
        randomizeGreenDroidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomizeGreenDroidPosition(); // Mengacak posisi droid merah di jalan
                repaint(); // Gambar ulang frame
            }

            private void randomizeGreenDroidPosition() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

                    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(randomizeButton);
        buttonPanel.add(randomizeRedDroidButton);
        
        add(buttonPanel, BorderLayout.EAST);
        
        initializeWalls();
        timer = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStarted) {
                // Perbarui posisi droid merah secara acak
                redDroidX = (int) (Math.random() * (MAP_SIZE - 2)) + 1;
                redDroidY = (int) (Math.random() * (MAP_SIZE - 2)) + 1;

                // Perbarui posisi droid hijau secara acak
                greenDroidX = (int) (Math.random() * (MAP_SIZE - 2)) + 1;
                greenDroidY = (int) (Math.random() * (MAP_SIZE - 2)) + 1;

                // Gambar ulang frame
                repaint();
            }
            }
        });
        
        initializeWalls();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
                
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Jika posisi adalah jalan, isi dengan warna putih
                if (walls[row][col]) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    // Jika tembok, isi dengan warna Hitam
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                }

                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
             // Gambar droid merah pada posisi (redDroidX, redDroidY)
                 g.setColor(Color.RED);
                 g.fillOval(redDroidX * CELL_SIZE, redDroidY * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Gambar droid hijau pada posisi (greenDroidX, greenDroidY)
                g.setColor(Color.GREEN);
                g.fillOval(greenDroidX * CELL_SIZE, greenDroidY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

    private void initializeWalls() {
            // Definisikan pola tembok maze
        walls = new boolean[][]{
            {true, true, true, true, true, true, true, true, true, true, true, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, true, false, false, true},
            {true, false, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, true, false, false, false, false, false, false, false, false, false, true, false, true},
            {true, false, true, false, true, true, true, true, true, true, true, false, true, false, true},
            {true, false, true, false, true, false, false, false, false, true, false, false, true, false, true},
            {true, false, true, false, true, false, true, true, false, true, true, true, true, false, true},
            {true, false, true, false, true, false, true, false, false, false, false, false, true, false, true},
            {true, false, true, true, true, true, true, false, true, true, true, false, true, false, true},
            {true, false, false, false, false, false, true, false, false, false, true, false, true, false, true},
            {true, true, true, true, true, false, true, true, true, true, true, false, true, false, true},
{true, false, false, false, false, false, false, false, false, false, false, false, true, false, true},
            {true, false, true, true, true, true, true, true, true, true, true, true, true, false, true},
            {true, false, true, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}
        };
    }
    private void randomizeWalls() {
    for (int row = 0; row < MAP_SIZE; row++) {
        for (int col = 0; col < MAP_SIZE; col++) {
            if (!isBorder(row, col)) {
                walls[row][col] = Math.random() < 0.5;
            }
        }
    }
}

    private void randomizeRedDroidPosition() {
    boolean validPosition = false;
    while (!validPosition) {
        redDroidX = (int) (Math.random() * (MAP_SIZE - 2)) + 1;
        redDroidY = (int) (Math.random() * (MAP_SIZE - 2)) + 1;
        if (!walls[redDroidY][redDroidX]) {
            validPosition = true;
        }
    }
}
    
private boolean isBorder(int row, int col) {
    return row == 0 || col == 0 || row == MAP_SIZE - 1 || col == MAP_SIZE - 1;
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        Paaproject peta = new Paaproject();
        peta.setVisible(true);
    });
}
}


