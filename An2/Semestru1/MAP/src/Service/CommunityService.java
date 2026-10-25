package Service;

import Domain.Friendship;
import Domain.User;

import java.util.*;

public class CommunityService {
    private UserService userService;
    private FriendshipService friendshipService;
    public CommunityService(UserService userService_, FriendshipService friendshipService_) {
        this.userService = userService_;
        this.friendshipService = friendshipService_;
    }
    private Map<Long, List<Long>> adjancencyList(){
        Map<Long, List<Long>> adjancencyList = new HashMap<>();
        for (User user: userService.getAllUser()){
            adjancencyList.put(user.getID(), new ArrayList<>());
        }
        for (Friendship friendship: friendshipService.getAllFriendships()){
            Long id1= friendship.getFirstfriend().getID();
            Long id2= friendship.getSecondfriend().getID();
            if (adjancencyList.containsKey(id1) && adjancencyList.containsKey(id2)){
                adjancencyList.get(id1).add(id2);
                adjancencyList.get(id2).add(id1);
            }
        }
        return adjancencyList;
    }
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
    public int getNumberOfCommunities(){
        Map<Long, List<Long>> adjancencyList = adjancencyList();
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
    public List<Long> getBiggestCommunity(){
        Map<Long, List<Long>> adjancencyList = adjancencyList();
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
