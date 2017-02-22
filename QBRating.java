/*
* Created on May 31, 2005
* ADALHED - Java applet to calculate NFL QB Rating
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.DecimalFormat;
import java.applet.*;



/**
* @author ADALHED
*
*/
public class QBRating extends Applet implements
ActionListener {
    
    JFrame ratingFrame;
    JPanel ratingPanel;
    JFormattedTextField attempts;
    JFormattedTextField completions;
    JFormattedTextField touchdowns;
    JFormattedTextField interceptions;
    JFormattedTextField totalyards;
    JLabel attemptLabel, completionLabel, tdLabel,intLabel, yardsLabel, ratingLabel;
    JButton calculateRating;
    
    /**
    * 1.  Create the QBRating:  needs the followingfields:
    *     Attempts
    *     Completions
    *     TD's
    *     Interceptions
    *     Yards
    
    * 2.  Need a 'calculate' button
    
    * 3.  Need a 'results' field
    
    * 4.  Do the following math
    * a = (((Comp/Att) * 100) -30) / 20
    * b = ((TDs/Att) * 100) / 5
    * c = (9.5 - ((Int/Att) * 100)) / 4
    * d = ((Yards/Att) - 3) / 4

    * a, b, c and d can not be greater than 2.375 or less than zero.

    * QB Rating = (a + b + c + d) / .06

    * 5.  output result
    */

    public QBRating() {
                
        //Create and set up the window.
        ratingFrame = new JFrame("Calculate NFL QB Rating");
      
	ratingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ratingFrame.setSize(new Dimension(340, 120));

        //Create and set up the panel.
        ratingPanel = new JPanel(new GridLayout(6, 2));
        
        //Create and add the text fields and labels.
        createFieldsAndLabels();
        
        //Set the default button.
      
	ratingFrame.getRootPane().setDefaultButton(calculateRating);

        //Add the panel to the window.
        ratingFrame.getContentPane().add(ratingPanel,BorderLayout.CENTER);

        //Display the window.
        ratingFrame.pack();
        ratingFrame.setVisible(true);
    }

    /**
    * Create and add the text fields and labels.
    */
    private void createFieldsAndLabels() {
            
	//Create the format for the Attempts textfield.
	attempts = new JFormattedTextField(new DecimalFormat("##0.0#"));
          
	attempts.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);

	try {
		attempts.setText("30.0");
                attempts.commitEdit();
            } catch(ParseException e) {
                //Shouldn't get here unless the setText value doesn't agree
                //with the format set above.
                e.printStackTrace();
            }

	//Create the format for the Completions text field.
	completions = new JFormattedTextField(new DecimalFormat("##0.0#"));
          
	completions.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);

	try {
                completions.setText("20.0");
                completions.commitEdit();
            } catch(ParseException e) {
                //Shouldn't get here unless the setText value doesn't agree
                //with the format set above.
                e.printStackTrace();
            }
            
	//Create the format for the Touchdowns text field.
	touchdowns = new JFormattedTextField(new DecimalFormat("##0.0#"));
          
	touchdowns.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);

	try {
                touchdowns.setText("2.0");
                touchdowns.commitEdit();
            } catch(ParseException e) {
                //Shouldn't get here unless the setText value doesn't agree
                //with the format set above.
                e.printStackTrace();
            }
            
	//Create the format for the Interceptions text field.
	interceptions = new JFormattedTextField(new DecimalFormat("##0.0#"));
          
	interceptions.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);

	try {
                interceptions.setText("1.0");
                interceptions.commitEdit();
            } catch(ParseException e) {
                //Shouldn't get here unless the setText value doesn't agree
                //with the format set above.
                e.printStackTrace();
            }
            
	//Create the format for the Total Yards text field.
	totalyards = new JFormattedTextField(new DecimalFormat("##0.0#"));
          
	totalyards.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);

	try {
                totalyards.setText("200.0");
                totalyards.commitEdit();
            } catch(ParseException e) {
                //Shouldn't get here unless the setText value doesn't agree
                //with the format set above.
                e.printStackTrace();
            }
            

	attemptLabel = new JLabel("Pass Attempts",SwingConstants.LEFT);
	completionLabel = new JLabel("Completions",SwingConstants.LEFT);
	tdLabel = new JLabel("Touchdowns",SwingConstants.LEFT);
	intLabel = new JLabel("Interceptions",SwingConstants.LEFT);
	yardsLabel = new JLabel("Total Yards",SwingConstants.LEFT);
	ratingLabel = new JLabel("QB Rating",SwingConstants.CENTER);
	calculateRating = new JButton("CalculateRating");

          
	attemptLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
          
	completionLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
          
	tdLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
          
	intLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
          
	yardsLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
          
	ratingLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
          
	//Listen to events from the text fields and calculate button.
	calculateRating.addActionListener(this);
	attempts.addActionListener(this);
	completions.addActionListener(this);
	touchdowns.addActionListener(this);	
	interceptions.addActionListener(this);	
	totalyards.addActionListener(this);
	
	//Add the widgets to the container.	
	ratingPanel.add(attemptLabel);
	ratingPanel.add(attempts);
	ratingPanel.add(completionLabel);
	ratingPanel.add(completions);
	ratingPanel.add(tdLabel);
	ratingPanel.add(touchdowns);
	ratingPanel.add(intLabel);
	ratingPanel.add(interceptions);
	ratingPanel.add(yardsLabel);
	ratingPanel.add(totalyards);
	ratingPanel.add(calculateRating);
	ratingPanel.add(ratingLabel);
            
            
        }
    
        public void actionPerformed(ActionEvent event) {
            String eventName = event.getActionCommand();
            //Parse text fields and calculate rating
            double compatt = (double)
		(((Double.parseDouble(completions.getText()))
                                /
		(Double.parseDouble(attempts.getText()))
                                * 100) - 30) * .05;
                if (compatt >= 2.375) {
                compatt = 2.375;
                }    else if (compatt <=0) {
                        compatt = 0;
                    }
                System.out.println(compatt);
            
            double tdatt = (double)
		((Double.parseDouble(touchdowns.getText()))
                                /
		(Double.parseDouble(attempts.getText()))
                                * 100) * .2;
                if (tdatt >= 2.375) {
                tdatt = 2.375;
                }    else if (tdatt <=0) {
                        tdatt = 0;
                    }
                System.out.println(tdatt);
                    
            double intatt = (double) (9.5 -
		((Double.parseDouble(interceptions.getText()))
                                /
		(Double.parseDouble(attempts.getText())))
                                * 100) / 4;
                if (intatt >= 2.375) {
                    intatt = 2.375;
                    }    else if (intatt <=0) {
                        intatt = 0;
                        }
                    System.out.println(intatt);            
            
            double yardatt = (double)
		((Double.parseDouble(totalyards.getText()))
                                / 
		(Double.parseDouble(attempts.getText()))
                                - 3)  / 4;
                if (yardatt >= 2.375) {
                    yardatt = 2.375;
                    }    else if (yardatt <=0) {
                        yardatt = 0;
                        }
                    System.out.println(yardatt);
                
            double endrating =  ((compatt + tdatt + intatt + yardatt))
                                    / .06;
            System.out.println(endrating);
            ratingLabel.setText("QB rating is " + endrating);            
            
        }    
        
        
        /**
        * Create the QBRating and show it.  For thread safety,
        * this method should be invoked from the
        * event-dispatching thread.
        */
        private static void createAndShowGUI() {
            //Make sure we have nice window decorations.
            JFrame.setDefaultLookAndFeelDecorated(true);

            QBRating ratingcalculator = new QBRating();
        }

        public static void main(String[] args) {
            //Schedule a job for the event-dispatching thread:
            //creating and showing this application's QBRating.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
        }
    }