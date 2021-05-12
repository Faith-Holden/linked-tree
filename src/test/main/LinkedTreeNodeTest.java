package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class LinkedTreeNodeTest {
    LinkedTreeNode <Integer> testTreeNodeInt;
    LinkedTreeNode <Boolean> testTreeNodeBool;
    LinkedTreeNode <String> testTreeNodeString;
    LinkedTreeNode <Double> testTreeNodeDouble;
    int[] intGen0 = {0};
    int[] intGen1Cous0 = {0,0};
    int[] intGen1Cous1 = {0,1};
    int[] intGen1Cous2 = {0,2};
    int[] intGen2Cous0 = {0,2,0};
    int[] intGen3Cous0 = {0,2,0,0};

    int[] boolGen0 = {0};
    int[] boolGen1Cous0 = {0,0};
    int[] boolGen1Cous1 = {0,1};
    int[] boolGen1Cous2 = {0,2};

    int[] doubleGen0 = {0};
    int[] doubleGen1Cous0 = {0,0};
    int[] doubleGen1Cous1 = {0,1};
    int[] doubleGen1Cous2 = {0,2};
    int[] doubleGen2Cous0 = {0,2,0};
    int[] doubleGen3Cous0 = {0,2,0,0};

    int[] stringGen0 = {0};
    int[] stringGen1Cous0 = {0,0};
    int[] stringGen1Cous1 = {0,1};
    int[] stringGen1Cous2 = {0,2};
    int[] stringGen2Cous0 = {0,2,0};
    int[] stringGen3Cous0 = {0,2,0,0};

    @BeforeEach
    void setUp() {
        testTreeNodeInt = new LinkedTreeNode<>(0);
        testTreeNodeInt.addChildNode(intGen0, 10);
        testTreeNodeInt.addChildNode(intGen0,11);
        testTreeNodeInt.addChildNode(intGen0,12);
        testTreeNodeInt.addChildNode(intGen1Cous0, 100);
        testTreeNodeInt.addChildNode(intGen1Cous0, 101);
        testTreeNodeInt.addChildNode(intGen1Cous1, 110);
        testTreeNodeInt.addChildNode(intGen1Cous1, 111);
        testTreeNodeInt.addChildNode(intGen1Cous2, 121);

        testTreeNodeBool = new LinkedTreeNode<>(false);
        testTreeNodeBool.addChildNode(boolGen0, false);
        testTreeNodeBool.addChildNode(boolGen0,true);
        testTreeNodeBool.addChildNode(boolGen0,false);
        testTreeNodeBool.addChildNode(boolGen1Cous0, true);
        testTreeNodeBool.addChildNode(boolGen1Cous0,false);
        testTreeNodeBool.addChildNode(boolGen1Cous1,true);
        testTreeNodeBool.addChildNode(boolGen1Cous1,false);
        testTreeNodeBool.addChildNode(boolGen1Cous2,true);

        testTreeNodeDouble = new LinkedTreeNode<>(0.0);
        testTreeNodeDouble.addChildNode(doubleGen0, 0.00);
        testTreeNodeDouble.addChildNode(doubleGen0,0.01);
        testTreeNodeDouble.addChildNode(doubleGen0,0.02);
        testTreeNodeDouble.addChildNode(doubleGen1Cous0, 0.000);
        testTreeNodeDouble.addChildNode(doubleGen1Cous0,0.001);
        testTreeNodeDouble.addChildNode(doubleGen1Cous1,0.010);
        testTreeNodeDouble.addChildNode(doubleGen1Cous1,0.011);
        testTreeNodeDouble.addChildNode(doubleGen1Cous2,0.021);

        testTreeNodeString = new LinkedTreeNode<>("0");
        testTreeNodeString.addChildNode(stringGen0,"00");
        testTreeNodeString.addChildNode( stringGen0,"01");
        testTreeNodeString.addChildNode(stringGen0,"02");
        testTreeNodeString.addChildNode(stringGen1Cous0, "000");
        testTreeNodeString.addChildNode(stringGen1Cous0,"001");
        testTreeNodeString.addChildNode(stringGen1Cous1,"010");
        testTreeNodeString.addChildNode(stringGen1Cous1,"011");
        testTreeNodeString.addChildNode(stringGen1Cous2,"021");
    }

    @Test
    void getNodeByLocation (){
        assertEquals(testTreeNodeInt, testTreeNodeInt.getNodeByLocation(intGen0));
        assertEquals(testTreeNodeInt.children.get(0), testTreeNodeInt.getNodeByLocation(intGen1Cous0));
        assertEquals(testTreeNodeInt.children.get(1), testTreeNodeInt.getNodeByLocation(intGen1Cous1));
        assertEquals(testTreeNodeInt.children.get(2), testTreeNodeInt.getNodeByLocation(intGen1Cous2));
        assertEquals(testTreeNodeInt.children.get(2).children.get(0), testTreeNodeInt.getNodeByLocation(intGen2Cous0));
    }

    @Test
    void testAddChildNode() {
        assertEquals(testTreeNodeInt.children.get(0).nodeData, 10);
        assertEquals(testTreeNodeInt.children.get(1).nodeData, 11);
        assertEquals(testTreeNodeInt.children.get(2).nodeData, 12);
        assertEquals(testTreeNodeInt.children.get(0).children.get(0).nodeData, 100);
        assertEquals(testTreeNodeInt.children.get(0).children.get(1).nodeData, 101);
        assertEquals(testTreeNodeInt.children.get(1).children.get(0).nodeData, 110);
        assertEquals(testTreeNodeInt.children.get(1).children.get(1).nodeData, 111);
        assertEquals(testTreeNodeInt.children.get(2).children.get(0).nodeData, 121);

        assertArrayEquals(testTreeNodeInt.children.get(0).nodeLocation, new int[] {0,0});
        assertArrayEquals(testTreeNodeInt.children.get(1).nodeLocation, new int[]{0, 1});
        assertArrayEquals(testTreeNodeInt.children.get(2).nodeLocation, new int[]{0, 2});
        assertArrayEquals(testTreeNodeInt.children.get(0).children.get(0).nodeLocation, new int[]{0, 0, 0});
        assertArrayEquals(testTreeNodeInt.children.get(0).children.get(1).nodeLocation, new int[]{0, 0, 1});
        assertArrayEquals(testTreeNodeInt.children.get(1).children.get(0).nodeLocation, new int[]{0, 1, 0});
        assertArrayEquals(testTreeNodeInt.children.get(1).children.get(1).nodeLocation, new int[]{0, 1, 1});
        assertArrayEquals(testTreeNodeInt.children.get(2).children.get(0).nodeLocation, new int[]{0, 2, 0});
    }

    @Test
    void testGetFirstOccurrence(){
        assertEquals(testTreeNodeInt.getFirstOccurrence(121),testTreeNodeInt.children.get(2).children.get(0));
        assertEquals(testTreeNodeString.getFirstOccurrence("021"),testTreeNodeString.children.get(2).children.get(0));
        assertEquals(testTreeNodeBool.getFirstOccurrence(true),testTreeNodeBool.children.get(0).children.get(0));
        assertEquals(testTreeNodeDouble.getFirstOccurrence(0.021),testTreeNodeDouble.children.get(2).children.get(0));

        assertEquals(testTreeNodeInt.getFirstOccurrence(0),testTreeNodeInt);
        assertEquals(testTreeNodeString.getFirstOccurrence("0"),testTreeNodeString);
        assertEquals(testTreeNodeBool.getFirstOccurrence(false),testTreeNodeBool);
        assertEquals(testTreeNodeDouble.getFirstOccurrence(0.0),testTreeNodeDouble);

        assertEquals(testTreeNodeInt.getFirstOccurrence(121),testTreeNodeInt.children.get(2).children.get(0));
        assertEquals(testTreeNodeString.getFirstOccurrence("021"),testTreeNodeString.children.get(2).children.get(0));
        assertEquals(testTreeNodeBool.getFirstOccurrence(true),testTreeNodeBool.children.get(0).children.get(0));
        assertEquals(testTreeNodeDouble.getFirstOccurrence(0.021),testTreeNodeDouble.children.get(2).children.get(0));

    }

    @Test
    void getAllOccurrences (){
        testTreeNodeDouble.addChildNode(doubleGen0,9.5);
        testTreeNodeDouble.addChildNode(doubleGen1Cous0,9.5);
        testTreeNodeDouble.addChildNode(doubleGen1Cous1,9.5);
        testTreeNodeDouble.addChildNode(doubleGen2Cous0, 9.5);
        testTreeNodeDouble.addChildNode(doubleGen3Cous0, 0.0);
        testTreeNodeDouble.addChildNode(doubleGen3Cous0, 0.0);

        testTreeNodeString.addChildNode(stringGen0,"9");
        testTreeNodeString.addChildNode(stringGen1Cous0,"9");
        testTreeNodeString.addChildNode(stringGen1Cous1,"9");
        testTreeNodeString.addChildNode(stringGen2Cous0,"9");
        testTreeNodeString.addChildNode(stringGen3Cous0,"0");
        testTreeNodeString.addChildNode(stringGen3Cous0,"0");

        testTreeNodeInt.addChildNode(intGen0, 9);
        testTreeNodeInt.addChildNode(intGen1Cous0, 9);
        testTreeNodeInt.addChildNode(intGen1Cous1, 9);
        testTreeNodeInt.addChildNode(intGen2Cous0, 9);
        testTreeNodeInt.addChildNode(intGen3Cous0, 0);
        testTreeNodeInt.addChildNode(intGen3Cous0, 0);

        ArrayList<LinkedTreeNode<Integer>> intNodeArrayList = new ArrayList<>();
        intNodeArrayList.add(testTreeNodeInt.children.get(3));
        intNodeArrayList.add(testTreeNodeInt.children.get(0).children.get(2));
        intNodeArrayList.add(testTreeNodeInt.children.get(1).children.get(2));
        intNodeArrayList.add(testTreeNodeInt.children.get(2).children.get(0).children.get(0));

        ArrayList<LinkedTreeNode<String>> stringNodeArrayList = new ArrayList<>();
        stringNodeArrayList.add(testTreeNodeString.children.get(3));
        stringNodeArrayList.add(testTreeNodeString.children.get(0).children.get(2));
        stringNodeArrayList.add(testTreeNodeString.children.get(1).children.get(2));
        stringNodeArrayList.add(testTreeNodeString.children.get(2).children.get(0).children.get(0));

        ArrayList<LinkedTreeNode<Double>> doubleNodeArrayList = new ArrayList<>();
        doubleNodeArrayList.add(testTreeNodeDouble.children.get(3));
        doubleNodeArrayList.add(testTreeNodeDouble.children.get(0).children.get(2));
        doubleNodeArrayList.add(testTreeNodeDouble.children.get(1).children.get(2));
        doubleNodeArrayList.add(testTreeNodeDouble.children.get(2).children.get(0).children.get(0));


        ArrayList <LinkedTreeNode<Integer>>intOccurrences = testTreeNodeInt.getAllOccurrences(9);
        ArrayList <LinkedTreeNode<String>> stringOccurrences = testTreeNodeString.getAllOccurrences("9");
        ArrayList <LinkedTreeNode<Double>> doubleOccurrences =testTreeNodeDouble.getAllOccurrences(9.5);


        assertEquals(new HashSet<> (intNodeArrayList), new HashSet<>(intOccurrences));
        assertEquals(new HashSet<> (stringNodeArrayList), new HashSet<> (stringOccurrences));
        assertEquals(new HashSet<> (doubleNodeArrayList), new HashSet<> (doubleOccurrences));

        assertEquals(intNodeArrayList.size(), intOccurrences.size());
        assertEquals(stringNodeArrayList.size(), stringOccurrences.size());
        assertEquals(doubleNodeArrayList.size(), doubleOccurrences.size());


    }

    @Test
    void setCousins (){
        assertNull(testTreeNodeInt.getNodeByLocation(intGen0).nextCousin);
        assertEquals(testTreeNodeInt.getNodeByLocation(new int[]{0,0}).nextCousin,testTreeNodeInt.getNodeByLocation(new int[] {0,1}));
        assertEquals(testTreeNodeInt.getNodeByLocation(new int[]{0,1}).nextCousin,testTreeNodeInt.getNodeByLocation(new int[] {0,2}));
        assertNull(testTreeNodeInt.getNodeByLocation(new int[]{0, 2}).nextCousin);
        assertNull(testTreeNodeInt.getNodeByLocation(new int[]{0, 2, 0}).nextCousin);


        assertNull(testTreeNodeInt.getNodeByLocation(intGen0).previousCousin);
        assertNull(testTreeNodeInt.getNodeByLocation(new int[]{0,0}).previousCousin);
        assertEquals(testTreeNodeInt.getNodeByLocation(new int[]{0,0}),testTreeNodeInt.getNodeByLocation(new int[] {0,1}).previousCousin);
        assertEquals(testTreeNodeInt.getNodeByLocation(new int[]{0,1}),testTreeNodeInt.getNodeByLocation(new int[] {0,2}).previousCousin);
        assertEquals(testTreeNodeInt.getNodeByLocation(new int[]{0, 1, 1}),testTreeNodeInt.getNodeByLocation(new int[]{0, 2, 0}).previousCousin);
    }

    @Test
    void searchFirstByBreadth (){
        assertEquals(testTreeNodeInt.nodeData, 0);
        assertEquals(testTreeNodeInt.children.get(0).nodeData, 10);
        assertEquals(testTreeNodeInt.children.get(1).nodeData, 11);
        assertEquals(testTreeNodeInt.children.get(2).nodeData, 12);
        assertEquals(testTreeNodeInt.children.get(0).children.get(0).nodeData, 100);
        assertEquals(testTreeNodeInt.children.get(0).children.get(1).nodeData, 101);
        assertEquals(testTreeNodeInt.children.get(1).children.get(0).nodeData, 110);
        assertEquals(testTreeNodeInt.children.get(1).children.get(1).nodeData, 111);
        assertEquals(testTreeNodeInt.children.get(2).children.get(0).nodeData, 121);
    }

    @Test
    void searchAllByBreath (){
        testTreeNodeInt.addChildNode(intGen0, 9);
        testTreeNodeInt.addChildNode(intGen1Cous0, 9);
        testTreeNodeInt.addChildNode(intGen1Cous1, 9);
        testTreeNodeInt.addChildNode(intGen2Cous0, 9);
        testTreeNodeInt.addChildNode(intGen3Cous0, 0);
        testTreeNodeInt.addChildNode(intGen3Cous0, 0);

        ArrayList<LinkedTreeNode<Integer>> intNodeArrayList = new ArrayList<>();
        intNodeArrayList.add(testTreeNodeInt.children.get(3));
        intNodeArrayList.add(testTreeNodeInt.children.get(0).children.get(2));
        intNodeArrayList.add(testTreeNodeInt.children.get(1).children.get(2));
        intNodeArrayList.add(testTreeNodeInt.children.get(2).children.get(0).children.get(0));

        ArrayList <LinkedTreeNode<Integer>>intOccurrences = testTreeNodeInt.searchAllByBreadth(9);
        assertEquals(new HashSet<> (intNodeArrayList), new HashSet<>(intOccurrences));
        assertEquals(intNodeArrayList.size(), intOccurrences.size());
    }

}
