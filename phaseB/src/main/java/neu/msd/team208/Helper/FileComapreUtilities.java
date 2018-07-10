package neu.msd.team208.Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import neu.msd.team208.controller.AdminPageController;

/**
 *  helper functions for file compare and AST tree creation from file
 * @author rachanatondare
 *
 */
public class FileComapreUtilities {
	
	private static final Logger logger = (Logger) LogFactory.getLog(AdminPageController.class);
	
	public String recurseTree(Node parent, int depth, File file) throws FileNotFoundException
    {
        String line = "";
        Scanner fileScan = null;
        String[] lineOfCode = null;
        
        fileScan = new Scanner(file);
        SimilarityCheckAlgorithm algoCheck ;
        int counter =0;
        if(fileScan.hasNext())
        {
            line = fileScan.nextLine();
        }

        while(!(line.isEmpty()))
        {
            logger.info(line);
            lineOfCode[counter] = line;
            
            int tabs = 0;
            for(int i = 0; i < line.length(); i++)
            {
                if(line.substring(i, i+1).equals("\t"))
                    tabs++;

            }
            if(tabs < depth)
                break;
            Node node = new Node();
            if(tabs >= depth)
            {
                if(parent != null)
                {
                	logger.info("Parent exists");
                }
                line = recurseTree(node, tabs, file);
            }
            algoCheck = new SimilarityCheckAlgorithm();
            algoCheck.printSimilar(lineOfCode);
            counter++;
        }
        
        return line;
    }
	
}
