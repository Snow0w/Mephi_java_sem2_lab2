package edu.mephi.gui;

import edu.mephi.exceptions.WrongFileFormatException;
import edu.mephi.reactor.Reactor;
import edu.mephi.reactor.ReactorStorage;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Gui extends JFrame implements ActionListener {
  private JPanel panelTop;
  private JButton chooseFile;
  private JButton renderTree;
  private JTextField fileName;
  private JScrollPane scroll;
  private JTree tree;
  private DefaultMutableTreeNode rootTree;
  private String selectedFileName;

  public Gui(String name) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());

    panelTop = new JPanel();
    chooseFile = new JButton("Choose file");
    renderTree = new JButton("Render tree");
    fileName = new JTextField();
    fileName.setEditable(false);
    panelTop.add(chooseFile);
    panelTop.add(renderTree);
    panelTop.add(fileName);
    this.tree = new JTree(rootTree);
    this.scroll = new JScrollPane(tree);
    this.add(panelTop, BorderLayout.NORTH);
    this.add(scroll, BorderLayout.CENTER);
    chooseFile.addActionListener(this);
    renderTree.addActionListener(this);
    fileName.setColumns(100);
    name = null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == chooseFile) {
      chooseFileAction();
    } else if (e.getSource() == renderTree) {
      renderTreeAction();
    }
  }

  private void renderTreeAction() {
    ReactorStorage storage = new ReactorStorage();
    try {
      storage.readFile(selectedFileName);
    } catch (WrongFileFormatException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Hell, no",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Something wrong with choosen file ",
                                    "Hell, no", JOptionPane.ERROR_MESSAGE);
      return;
    }
    rootTree = new DefaultMutableTreeNode(
        new String("Реакторы (" + storage.getType() + ")"));
    tree = new JTree(rootTree);
    updateTreeView(storage);
    scroll.setViewportView(tree);
  }

  private void updateTreeView(ReactorStorage storage) {
    DefaultMutableTreeNode varNode;
    DefaultMutableTreeNode newChild;

    for (Map.Entry<String, Reactor> entry : storage.getStorage().entrySet()) {
      varNode = new DefaultMutableTreeNode(entry.getKey());
      rootTree.add(varNode);
      newChild = new DefaultMutableTreeNode("class: " +
                                            entry.getValue().getClassReactor());
      varNode.add(newChild);
      newChild = new DefaultMutableTreeNode(
          "burnup: " + Double.toString(entry.getValue().getBurnup()));
      varNode.add(newChild);
      newChild = new DefaultMutableTreeNode(
          "kpd: " + Double.toString(entry.getValue().getKpd()));
      varNode.add(newChild);
      newChild = new DefaultMutableTreeNode(
          "encriment: " + Double.toString(entry.getValue().getEnrichment()));
      varNode.add(newChild);
      newChild = new DefaultMutableTreeNode(
          "termal capacity: " +
          Double.toString(entry.getValue().getTermalCapacity()));
      varNode.add(newChild);
      newChild = new DefaultMutableTreeNode(
          "electrical capacity: " +
          Double.toString(entry.getValue().getElectricalCapacity()));
      varNode.add(newChild);
      newChild = new DefaultMutableTreeNode(
          "life time: " + Double.toString(entry.getValue().getLifeTime()));
      varNode.add(newChild);
      newChild = new DefaultMutableTreeNode(
          "first load: " + Double.toString(entry.getValue().getFirstLoad()));
      varNode.add(newChild);
    }
  }

  private void chooseFileAction() {
    JFileChooser fileopen =
        new JFileChooser("/home/snow0w/repos/JAVA/lab2/files/");
    int ret = fileopen.showDialog(null, "Choose file");
    if (ret != JFileChooser.APPROVE_OPTION) {
      return;
    }
    selectedFileName = fileopen.getSelectedFile().getAbsolutePath();
    System.out.println(selectedFileName);
    fileName.setText(selectedFileName);
  }
}
