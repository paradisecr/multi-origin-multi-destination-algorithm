package me.rui;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.MessageFormat;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        String filePath;
        if (args.length < 1) {
            System.out.print("Please input file path:");
            filePath = (new Scanner(System.in)).next();
            filePath = filePath.trim();
        } else {
            filePath = args[0];
        }
        try {
            int nodeNum;
            int linkNum;
            int consumerNum;
            int serverCostPerUnit;
            int[][] linkCapacity;
            int[][] linkCostPerUnit;
            int[] nodeDemand;
            // Open file
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            // Read nodeNum linkNum consumerNum
            String line = scanner.nextLine();
            String[] data = line.split("\\s+");
            nodeNum = Integer.valueOf(data[0]);
            linkNum = Integer.valueOf(data[1]);
            consumerNum = Integer.valueOf(data[2]);

            line = scanner.nextLine();
            line = scanner.nextLine();
            data = line.split("\\s+");
            serverCostPerUnit = Integer.valueOf(data[0]);

            // Read blank line
            line = scanner.nextLine();
            line = scanner.nextLine();
            // Read linkInfo(startNode endNode  capability unitCost) * linkNum
            linkCapacity = new int[nodeNum][nodeNum];
            linkCostPerUnit = new int[nodeNum][nodeNum];
            line = scanner.nextLine();
            while(!StringUtils.isBlank(line)) {
                data = line.split("\\s+");
                linkCapacity[Integer.valueOf(data[0])][Integer.valueOf(data[1])] = Integer.valueOf(data[2]);
                linkCostPerUnit[Integer.valueOf(data[0])][Integer.valueOf(data[1])] = Integer.valueOf(data[3]);
                line = scanner.nextLine();
            }
            // Read blank line
            // Read consumerInfo(consumerId connectedNode request) * consumerNum
            line = scanner.nextLine();
            nodeDemand = new int[nodeNum];
            while(!StringUtils.isBlank(line)) {
                data = line.split("\\s+");
                nodeDemand[Integer.valueOf(data[1])] = Integer.valueOf(data[2]);
                if (scanner.hasNext()) {
                    line = scanner.nextLine();
                } else {
                    break;
                }
            }
            String nodeNStr = MessageFormat.format("nodeN={0};", nodeNum);
            String serverCostPerUnitStr = MessageFormat.format("serverCostPerUnit={0};", serverCostPerUnit);

            String linkCapacityStr;
            StringBuilder linkCapacityBuilder = new StringBuilder();
            linkCapacityBuilder.append("linkCapacity = [");
            for (int i=0; i< linkCapacity.length; i++) {
                if (i !=0) linkCapacityBuilder.append(",");
                linkCapacityBuilder.append("\n\r");
                linkCapacityBuilder.append("[");
                for (int j =0; j < linkCapacity[i].length; j++) {
                    if (j !=0 ) linkCapacityBuilder.append(",");
                    linkCapacityBuilder.append(linkCapacity[i][j]);
                }
                linkCapacityBuilder.append("]");
            }
            linkCapacityBuilder.append("]");
            linkCapacityBuilder.append(";");
            linkCapacityStr = linkCapacityBuilder.toString();

            String linkCostPerUnitStr = linkCapacityBuilder.toString();
            StringBuilder linkCostPerUnitBuilder = new StringBuilder();
            linkCostPerUnitBuilder.append("linkCostPerUnit = [");
            for (int i=0; i< linkCostPerUnit.length; i++) {
                if (i !=0) linkCostPerUnitBuilder.append(",");
                linkCostPerUnitBuilder.append("\n\r");
                linkCostPerUnitBuilder.append("[");
                for (int j =0; j < linkCostPerUnit[i].length; j++) {
                    if (j !=0 ) linkCostPerUnitBuilder.append(",");
                    linkCostPerUnitBuilder.append(linkCostPerUnit[i][j]);
                }
                linkCostPerUnitBuilder.append("]");
                linkCostPerUnitBuilder.append(";");
            }
            linkCostPerUnitBuilder.append("]");
            linkCostPerUnitStr = linkCostPerUnitBuilder.toString();

            String nodeDemandStr;
            StringBuilder nodeDemandBuilder = new StringBuilder();
            nodeDemandBuilder.append("nodeDemand=[");
            for (int i = 0; i< nodeDemand.length; i++) {
                if (i !=0 ) nodeDemandBuilder.append(",");
                nodeDemandBuilder.append(nodeDemand[i]);
            }
            nodeDemandBuilder.append("];");
            nodeDemandStr = nodeDemandBuilder.toString();
            System.out.println(nodeNStr);
            System.out.println(serverCostPerUnitStr);
            System.out.println(linkCapacityStr);
            System.out.println(linkCostPerUnitStr);
            System.out.println(nodeDemandStr);

        } catch (Exception e) {
            System.out.print(e);
        } finally {

        }

    }

}