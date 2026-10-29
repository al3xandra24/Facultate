package Service;

import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Repository.Repository;

import java.util.*;

public class CommunityService {
    private final Repository<Long, User> userRepository;
    private final Repository<Tuple<Long, Long>, Friendship> friendshipRepository;

    /**
     * Creates the service
     * @param userRepository_ the repository where users are memorated
     * @param friendshipRepository_
     */
    public CommunityService(Repository<Long, User> userRepository_, Repository<Tuple<Long, Long>, Friendship> friendshipRepository_) {
        this.userRepository = userRepository_;
        this.friendshipRepository = friendshipRepository_;
    }

    /**
     * Creates an adjacency list with the undirected graph composed of all the friendships between users
     * @return the adjacency list
     */
    private Map<Long, List<Long>> adjacencyList(){
        Map<Long, List<Long>> adjancencyList = new HashMap<>();
        for (User user: userRepository.findAll()){
            adjancencyList.put(user.getID(), new ArrayList<>());
        }
        for (Friendship friendship: friendshipRepository.findAll()){
            Long id1= friendship.getFirstfriend();
            Long id2= friendship.getSecondfriend();
            if (adjancencyList.containsKey(id1) && adjancencyList.containsKey(id2)){
                adjancencyList.get(id1).add(id2);
                adjancencyList.get(id2).add(id1);
            }
        }
        return adjancencyList;
    }

    /**
     * Realizes a dfs traversal of the graph
     * @param startNode the node from which it begins
     * @param visitedNodes the nodes already visited
     * @param adjancencyList the adjacency list
     * @return the current connected component
     */
    private List<Long> dfs(Long startNode, Set<Long> visitedNodes, Map<Long, List<Long>> adjancencyList){
        List<Long> component=new ArrayList<>();
        Stack<Long> stack=new Stack<>();
        stack.push(startNode);
        visitedNodes.add(startNode);
        while (!stack.isEmpty()){
            Long node = stack.pop();
            component.add(node);
            for (Long neighbour: adjancencyList.get(node)){
                if (!visitedNodes.contains(neighbour)){
                    visitedNodes.add(neighbour);
                    stack.push(neighbour);
                }
            }
        }
       return component;
    }

    /**
     * Retrieves the number of communities between users
     * @return the number of communities
     */
    public int getNumberOfCommunities(){
        Map<Long, List<Long>> adjancencyList = adjacencyList();
        Set<Long> visitedNodes = new HashSet<>();
        int communityNumber=0;
        for (Long node: adjancencyList.keySet()){
            if (!visitedNodes.contains(node)){
                dfs(node,visitedNodes,adjancencyList);
                communityNumber++;
            }
        }
        return communityNumber;
    }

    /**
     * Retrieves the most sociable community
     * @return the most sociable community
     */
    public List<Long> getBiggestCommunity(){
        Map<Long, List<Long>> adjancencyList = adjacencyList();
        Set<Long> visitedNodes = new HashSet<>();
        List<Long> biggestCommunity = new ArrayList<>();
        for (Long node: adjancencyList.keySet()){
            if (!visitedNodes.contains(node)){
                List<Long> component=dfs(node,visitedNodes,adjancencyList);
                if  (component.size()>biggestCommunity.size()){
                    biggestCommunity=component;
                }
            }
        }
        return biggestCommunity;
    }
}
