/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016;

import java.util.ArrayList;

/**
 *
 * @author Shane
 */
public class Drone {

    private int idNumber;
    private int[] inventory;
    private int currentWeight;
    private static int carryingCapacity;
    private int rowPos;
    private int colPos;
    boolean available;
    int nextTurnAvailable;
    int turn;

    public Drone(int idNumber, int numberOfItems, int capacity, int startingRow, int startingCol) {
        this.idNumber = idNumber;
        inventory = new int[numberOfItems];
        currentWeight = 0;
        carryingCapacity = capacity;

        this.rowPos = startingRow;
        this.colPos = startingCol;
    }

    public boolean canLoadWeight(int weight) {
        return currentWeight + weight <= carryingCapacity;
    }

    public boolean load(int numberOfItems, int itemType, int itemWeight) {
        if (itemWeight * numberOfItems + currentWeight > carryingCapacity) {
            return false;
        }
        inventory[itemType] += numberOfItems;
        currentWeight += itemWeight * numberOfItems;
        return true;
    }

    public boolean unload(int numberOfItems, int itemType, int itemWeight) {
        if (inventory[itemType] - numberOfItems < 0) {
            return false;
        }
        inventory[itemType] -= numberOfItems;
        return true;
    }

    // Returns int of distance to [rowB, colB]
    public int getDistance(int rowB, int colB) {
        double form1 = (this.rowPos - rowB) * (this.rowPos - rowB);
        double form2 = (this.colPos - colB) * (this.colPos - colB);

        return (int) Math.ceil((Math.sqrt(form1 + form2)));
    }

    private int getDistanceBetween(int rowA, int colA, int rowB, int colB) {
        double form1 = (rowA - rowB) * (rowA - rowB);
        double form2 = (colA - colB) * (colA - colB);

        return (int) Math.ceil((Math.sqrt(form1 + form2)));
    }

    // Returns sorted array of closest warehouses from this drone
    // Format: shortest to furthest
    public Warehouse[] getClosestWarehouses(Warehouse[] warehouses) {
        int distances[] = new int[warehouses.length];

        for (int i = 0; i < warehouses.length; i++) {
            int distance = getDistance(
                    warehouses[i].getRow(), warehouses[i].getColumn()
            );
            distances[i] = distance;
        }

        warehouses = pairedInsertionSort(distances, warehouses);

        return warehouses;
    }

    // Returns sorted array of closest trip from this drone, warehouse, order 
    // Format: shortest to furthest
    public Warehouse[] getClosestTrip(Warehouse[] warehouses, Order order) {
        int distances[] = new int[warehouses.length];
        int orderRow = order.row;
        int orderCol = order.col;

        for (int i = 0; i < warehouses.length; i++) {
            int distance = getDistance(
                    warehouses[i].getRow(), warehouses[i].getColumn()
            );

            distance += getDistanceBetween(
                    warehouses[i].getRow(), warehouses[i].getColumn(),
                    orderRow, orderCol
            );

            distances[i] = distance;
        }

        warehouses = pairedInsertionSort(distances, warehouses);

        return warehouses;
    }

    // Sort an array of Warehouses via the distance from this drone
    private Warehouse[] pairedInsertionSort(int[] distances, Warehouse[] warehouses) {
        int arraySize = distances.length;

        for (int i = 1; i < arraySize; i++) {
            int j = i;

            int toInsert = distances[i];
            Warehouse toInsertWarehouse = warehouses[i];

            while ((j > 0) && (distances[j - 1]) > toInsert) {
                distances[j] = distances[j - 1];
                warehouses[j] = warehouses[j - 1];
                j--;

            }

            distances[j] = toInsert;
            warehouses[j] = toInsertWarehouse;
        }

        return warehouses;
    }

    public Order processOrder(Order order, Warehouse[] warehouses) {
        warehouses = getClosestWarehouses(warehouses);
        ArrayList<Integer> items = order.items;

        // loop until a warehouse with at least one wanted item is found.
        boolean warehouseFound = false;
        for (Warehouse house : warehouses) {
            for (int i = 0; i < items.size(); i++) {
                int item = items.get(i);
                if (item == -1) {
                    break;
                }
                int itemWeight = ItemWeights.itemWeights[i];
                // load the items

                if (validateActionLength(getDistanceBetween(rowPos, colPos, house.getRow(), house.getColumn()), getDistanceBetween(house.getRow(), house.getColumn(), order.row, order.col))) {
                    if (canLoadWeight(itemWeight) && house.hasItem(item)) {
                        warehouseFound = true;
                        if (load(1, item, itemWeight)
                                || house.loadOntoDrone(item, 1)) {
                            // print load commands
                            System.out.println(idNumber + " L " + house.idNumber + " " + item + " 1");
                        } else {
                            System.err.println("Oh Shit!");
                        }
                        items.set(item, -1);
                        updateAvailability(getDistanceBetween(rowPos, colPos, house.getRow(), house.getColumn()), getDistanceBetween(house.getRow(), house.getColumn(), order.row, order.col));
                        System.out.println(idNumber + " D " + order.id + " " + item + " 1");
                    }
                }

            }
            if (warehouseFound) {
                break;
            }
        }

        return null; // <-- PLACEHOLDER
    }
    
    private void updateAvailability(int distance1, int distance2){
        nextTurnAvailable = turn + distance1 + distance2  + 2;
        available = false;
    }
    
    private boolean validateActionLength(int distance1, int distance2){
        return distance1 + distance2 + 2 + turn < Deadline.DEADLINE;
    }

    public boolean incrementTurn() {
        turn++;
        if (turn >= nextTurnAvailable) {
            available = true;
        }
        return available;
    }

}
