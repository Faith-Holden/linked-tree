package main;
import java.util.*;

class  LinkedTreeNode <Type>{
    LinkedTreeNode (Type initialNodeData) {
        nodeData = initialNodeData;
        nodeLayer = 0;
        children = new ArrayList<>();
        descendantGens = new ArrayList<>();
        this.descendantGens.add(this);
    }

    Type nodeData;
    int nodeLayer;
    LinkedTreeNode <Type> previousCousin;
    LinkedTreeNode <Type> nextCousin;
    LinkedTreeNode <Type> parentNode;
    ArrayList <LinkedTreeNode <Type>> children;
    ArrayList <LinkedTreeNode <Type>> descendantGens;;

    int [] nodeLocation = {0};
    
    void addChildNode (int[] parentLocation, Type nodeData){
        LinkedTreeNode <Type> childNode = new LinkedTreeNode<>(nodeData);
        LinkedTreeNode <Type> parent = getNodeByLocation(parentLocation);

        parent.children.add(childNode);
        childNode.parentNode =parent;
        childNode.nodeLayer = parent.nodeLayer+1;
        childNode.nodeLocation = new int[childNode.nodeLayer+1];
        System.arraycopy(parent.nodeLocation, 0, childNode.nodeLocation, 0, parent.nodeLocation.length);

        childNode.nodeLocation[childNode.nodeLocation.length-1]=parent.children.size()-1;

        setGenerationArray(childNode);

        setCousins(determinePreviousCousin(childNode), childNode);
        setCousins(childNode, determineNextCousin(childNode));
    }

    LinkedTreeNode <Type> getFirstOccurrence (Type searchTerm) {
        if (nodeData.equals(searchTerm)) {
            return this;
        }
        for (LinkedTreeNode<Type> child : children){
            LinkedTreeNode <Type> tempChild = child.getFirstOccurrence(searchTerm);
            if (tempChild!=null) {
                return tempChild;
            }
        }
        return null;
    }

    ArrayList <LinkedTreeNode <Type>> getAllOccurrences (Type searchTerm){
        ArrayList <LinkedTreeNode <Type>> foundNodes = new ArrayList<>();
        if (!children.isEmpty()){

            if (this.nodeData.equals (searchTerm)){
                foundNodes.add(this);
            }
            for (LinkedTreeNode <Type> child : children){
                foundNodes.addAll(child.getAllOccurrences(searchTerm));
            }

        }
        else {
            if (nodeData.equals (searchTerm)){
                foundNodes.add(this);
            }
        }
        return foundNodes;
    }

    LinkedTreeNode <Type> getNodeByLocation (int [] getLocation){
        LinkedTreeNode <Type> tempChild = this;
        while(tempChild.parentNode!=null){
            tempChild = tempChild.parentNode;
        }
        for(int i= 1; i< getLocation.length; i++){
            if (Arrays.equals(tempChild.nodeLocation, getLocation)){
                break;
            }
            if (tempChild.children.size()<getLocation[i]){
                return null;
            }
            else{
                tempChild = tempChild.children.get(getLocation[i]);
            }
        }
        return tempChild;
    }

    LinkedTreeNode <Type> searchForFirstByBreadth (Type searchTerm){
        LinkedTreeNode <Type> tempNode;
        for (LinkedTreeNode <Type> generation : descendantGens){
            tempNode = generation;
            while (tempNode.nodeData!=searchTerm&&tempNode.nextCousin!=null){
                    tempNode = tempNode.nextCousin;
            }
            if (tempNode.nodeData==searchTerm){
                return tempNode;
            }
        }
        return null;
    }

    ArrayList<LinkedTreeNode<Type>> searchAllByBreadth (Type searchTerm){
        ArrayList <LinkedTreeNode<Type>> foundNodes = new ArrayList<>();
        LinkedTreeNode <Type> tempNode;
        for (LinkedTreeNode <Type> generation : descendantGens){
            tempNode = generation;
            while (tempNode.nextCousin!=null){
                if (tempNode.nodeData==searchTerm){
                    foundNodes.add(tempNode);
                }
                tempNode = tempNode.nextCousin;
            }
            if (tempNode.nodeData==searchTerm){
                foundNodes.add(tempNode);
            }
        }
        return foundNodes;
    }

    boolean checkIfIsEarlier (LinkedTreeNode <Type> checkedNode, LinkedTreeNode <Type> node2){
        int index = 0;
        while (index<checkedNode.nodeLocation.length){
            if (checkedNode.nodeLocation[index]<node2.nodeLocation[index]){
                return true;
            } else if (checkedNode.nodeLocation[index]>node2.nodeLocation[index]){
                return false;
            }

            if (index==checkedNode.nodeLocation.length-1){
                return false;
            }
            index++;
        }
        return false;
    }

    private void setCousins (LinkedTreeNode<Type> priorCousin, LinkedTreeNode<Type> latterCousin){
        if(priorCousin!=null){
            priorCousin.nextCousin = latterCousin;
        }
        if (latterCousin!=null){
            latterCousin.previousCousin=priorCousin;
        }
    }

    private void setGenerationArray (LinkedTreeNode<Type>childNode){
        LinkedTreeNode <Type> firstAncestor = getNodeByLocation(new int [] {0});
        LinkedTreeNode <Type> tempNode;

        if (firstAncestor.descendantGens.size()<childNode.nodeLocation.length){
            firstAncestor.descendantGens.add(childNode);
        } else {
            tempNode=firstAncestor.descendantGens.get(childNode.nodeLayer);
            if (checkIfIsEarlier(childNode, tempNode)){
                this.descendantGens.set(childNode.nodeLayer, childNode);
            }
        }
    }

    private LinkedTreeNode<Type> determinePreviousCousin(LinkedTreeNode<Type> childNode){
        LinkedTreeNode<Type> tempNode = descendantGens.get(childNode.nodeLayer);

        if (tempNode == null || tempNode == childNode || checkIfIsEarlier(childNode, tempNode)){
            return null;
        }
        while (tempNode.nextCousin!=null){
            if (checkIfIsEarlier(tempNode, childNode)&& checkIfIsEarlier(childNode, tempNode.nextCousin)){
                return tempNode;
            }
            else{
                tempNode = tempNode.nextCousin;
            }
        }
        if(checkIfIsEarlier(tempNode, childNode)){
            return tempNode;
        }
        return null;
    }

    private LinkedTreeNode <Type> determineNextCousin ( LinkedTreeNode<Type> childNode){
        LinkedTreeNode<Type> tempNode;
        if (descendantGens.size()>= childNode.nodeLayer){
            tempNode= descendantGens.get(childNode.nodeLayer);
        } else{
            return null;
        }
        if (Arrays.equals(tempNode.nodeLocation, childNode.nodeLocation)&&tempNode.nextCousin==null){
            return null;
        }
        do {
            if(checkIfIsEarlier(childNode, tempNode)){
                if(tempNode.previousCousin==null){
                    return tempNode;
                }
            } else{
                if (tempNode.nextCousin==null){
                    return null;
                }else if(checkIfIsEarlier(childNode, tempNode.nextCousin)){
                    return tempNode.nextCousin;
                }
            }
            tempNode = tempNode.nextCousin;
        }while (true);
    }
}