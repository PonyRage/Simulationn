package pathfinder;

import coordinates.Coordinates;
import entities.*;

import world.World;

import java.util.*;

public class PathFinding {

    public List<Coordinates> findPath(Sheep sheep, Flower target, World world) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(Node::getF));
        HashSet<Coordinates> closedList = new HashSet<>();
        Map<Coordinates, Node> allNodes = new HashMap<>();

        Node startNode = new Node(sheep.getCoordinates(), null, 0,
                getHeuristic(sheep.getCoordinates(), target.getCoordinates()));
        openList.add(startNode);
        allNodes.put(startNode.getCoordinates(), startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            System.out.println("Current node: " + currentNode.getCoordinates());

            if (currentNode.getCoordinates().equals(target.getCoordinates())) {
                System.out.println("Path found!");
                return reconstructPath(currentNode);
            }

            closedList.add(currentNode.getCoordinates());

            for (Coordinates neighbour : getNeighbours(currentNode.getCoordinates(), world)) {
                if (closedList.contains(neighbour)) continue;

                double tentativeG = currentNode.getG() + 1;

                if (!allNodes.containsKey(neighbour)) {
                    Node neighbourNode = new Node(
                            neighbour, currentNode, tentativeG,
                            getHeuristic(neighbour, target.getCoordinates())
                    );
                    openList.add(neighbourNode);
                    allNodes.put(neighbour, neighbourNode);
                } else if (tentativeG < allNodes.get(neighbour).getG()) {
                    Node neighbourNode = allNodes.get(neighbour);
                    neighbourNode.setParent(currentNode);
                    neighbourNode.setG(tentativeG);
                    openList.add(neighbourNode);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinates> getNeighbours(Coordinates coordinates, World world) {
        List<Coordinates> neighbours = new ArrayList<>();
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

        for (int[] direction : directions) {
            int newX = coordinates.getX() + direction[0];
            int newY = coordinates.getY() + direction[1];

            Coordinates newCoord = new Coordinates(newX, newY);

            if (newX >= 0 && newY >= 0 && newX < world.col && newY < world.row) {
                Entity entity = world.getEntities().get(newCoord);
                if (!(entity instanceof Rock) && !(entity instanceof Tree)) {
                    neighbours.add(newCoord);
                }
            }
        }
        return neighbours;
    }

    private double getHeuristic(Coordinates current, Coordinates target) {
        return Math.abs(current.getX() - target.getX()) + Math.abs(current.getY() - target.getY());
    }
    private List<Coordinates> reconstructPath(Node node) {
        List<Coordinates> path = new ArrayList<>();

        while (node != null) {
            path.add(node.getCoordinates());
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    static class Node {
        private final Coordinates coordinates;
        private Node parent;
        private double g;
        private final double h;

        public Node(Coordinates coordinates, Node parent, double g, double h) {
            this.coordinates = coordinates;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        private double getG() {
            return g;
        }

        public void setG(double g) {
            this.g = g;
        }

        private double getH() {
            return h;
        }

        public double getF() {
            return g + h;
        }
    }
}
