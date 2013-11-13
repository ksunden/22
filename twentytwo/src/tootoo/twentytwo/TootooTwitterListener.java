package tootoo.twentytwo;

import java.util.Map;

import twitter4j.AccountSettings;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.OEmbed;
import twitter4j.PagableResponseList;
import twitter4j.Place;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.SimilarPlaces;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.api.HelpResources.Language;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TootooTwitterListener implements TwitterListener{
    
    @Override
    public void checkedUserListMembership(User arg0){
        
    }
    
    @Override
    public void checkedUserListSubscription(User arg0){
        
    }
    
    @Override
    public void createdBlock(User arg0){
        
    }
    
    @Override
    public void createdFavorite(Status arg0){
        
    }
    
    @Override
    public void createdFriendship(User arg0){
        
    }
    
    @Override
    public void createdPlace(Place arg0){
        
    }
    
    @Override
    public void createdSavedSearch(SavedSearch arg0){
        
    }
    
    @Override
    public void createdUserList(UserList arg0){
        
    }
    
    @Override
    public void createdUserListMember(UserList arg0){
        
    }
    
    @Override
    public void createdUserListMembers(UserList arg0){
        
    }
    
    @Override
    public void destroyedBlock(User arg0){
        
    }
    
    @Override
    public void destroyedDirectMessage(DirectMessage arg0){
        
    }
    
    @Override
    public void destroyedFavorite(Status arg0){
        
    }
    
    @Override
    public void destroyedFriendship(User arg0){
        
    }
    
    @Override
    public void destroyedSavedSearch(SavedSearch arg0){
        
    }
    
    @Override
    public void destroyedStatus(Status arg0){
        
    }
    
    @Override
    public void destroyedUserList(UserList arg0){
        
    }
    
    @Override
    public void destroyedUserListMember(UserList arg0){
        
    }
    
    @Override
    public void gotAPIConfiguration(TwitterAPIConfiguration arg0){
        
    }
    
    @Override
    public void gotAccountSettings(AccountSettings arg0){
        
    }
    
    @Override
    public void gotAvailableTrends(ResponseList<Location> arg0){
        
    }
    
    @Override
    public void gotBlockIDs(IDs arg0){
        
    }
    
    @Override
    public void gotBlocksList(ResponseList<User> arg0){
        
    }
    
    @Override
    public void gotClosestTrends(ResponseList<Location> arg0){
        
    }
    
    @Override
    public void gotContributees(ResponseList<User> arg0){
        
    }
    
    @Override
    public void gotContributors(ResponseList<User> arg0){}
    
    @Override
    public void gotDirectMessage(DirectMessage arg0){
        
    }
    
    @Override
    public void gotDirectMessages(ResponseList<DirectMessage> arg0){
        
    }
    
    @Override
    public void gotFavorites(ResponseList<Status> arg0){
        
    }
    
    @Override
    public void gotFollowersIDs(IDs arg0){
        
    }
    
    @Override
    public void gotFollowersList(PagableResponseList<User> arg0){
        
    }
    
    @Override
    public void gotFriendsIDs(IDs arg0){
        
    }
    
    @Override
    public void gotFriendsList(PagableResponseList<User> arg0){
        
    }
    
    @Override
    public void gotGeoDetails(Place arg0){
        
    }
    
    @Override
    public void gotHomeTimeline(ResponseList<Status> arg0){
        
    }
    
    @Override
    public void gotIncomingFriendships(IDs arg0){
        
    }
    
    @Override
    public void gotLanguages(ResponseList<Language> arg0){
        
    }
    
    @Override
    public void gotMemberSuggestions(ResponseList<User> arg0){
        
    }
    
    @Override
    public void gotMentions(ResponseList<Status> arg0){
        
    }
    
    @Override
    public void gotOAuthAccessToken(AccessToken arg0){
        
    }
    
    @Override
    public void gotOAuthRequestToken(RequestToken arg0){
        
    }
    
    @Override
    public void gotOEmbed(OEmbed arg0){
        
    }
    
    @Override
    public void gotOutgoingFriendships(IDs arg0){
        
    }
    
    @Override
    public void gotPlaceTrends(Trends arg0){
        
    }
    
    @Override
    public void gotPrivacyPolicy(String arg0){
        
    }
    
    @Override
    public void gotRateLimitStatus(Map<String, RateLimitStatus> arg0){
        
    }
    
    @Override
    public void gotRetweets(ResponseList<Status> arg0){
        
    }
    
    @Override
    public void gotRetweetsOfMe(ResponseList<Status> arg0){
        
    }
    
    @Override
    public void gotReverseGeoCode(ResponseList<Place> arg0){
        
    }
    
    @Override
    public void gotSavedSearch(SavedSearch arg0){
        
    }
    
    @Override
    public void gotSavedSearches(ResponseList<SavedSearch> arg0){
        
    }
    
    @Override
    public void gotSentDirectMessages(ResponseList<DirectMessage> arg0){
        
    }
    
    @Override
    public void gotShowFriendship(Relationship arg0){
        
    }
    
    @Override
    public void gotShowStatus(Status arg0){
        
    }
    
    @Override
    public void gotShowUserList(UserList arg0){
        
    }
    
    @Override
    public void gotSimilarPlaces(SimilarPlaces arg0){
        
    }
    
    @Override
    public void gotSuggestedUserCategories(ResponseList<Category> arg0){
        
    }
    
    @Override
    public void gotTermsOfService(String arg0){
        
    }
    
    @Override
    public void gotUserDetail(User arg0){
        
    }
    
    @Override
    public void gotUserListMembers(PagableResponseList<User> arg0){
        
    }
    
    @Override
    public void gotUserListMemberships(PagableResponseList<UserList> arg0){
        
    }
    
    @Override
    public void gotUserListStatuses(ResponseList<Status> arg0){
        
    }
    
    @Override
    public void gotUserListSubscribers(PagableResponseList<User> arg0){
        
    }
    
    @Override
    public void gotUserListSubscriptions(PagableResponseList<UserList> arg0){
        
    }
    
    @Override
    public void gotUserLists(ResponseList<UserList> arg0){
        
    }
    
    @Override
    public void gotUserSuggestions(ResponseList<User> arg0){
        
    }
    
    @Override
    public void gotUserTimeline(ResponseList<Status> arg0){
        
    }
    
    @Override
    public void lookedUpFriendships(ResponseList<Friendship> arg0){
        
    }
    
    @Override
    public void lookedupUsers(ResponseList<User> arg0){
        
    }
    
    @Override
    public void onException(TwitterException arg0, TwitterMethod arg1){
        
    }
    
    @Override
    public void removedProfileBanner(){
        
    }
    
    @Override
    public void reportedSpam(User arg0){
        
    }
    
    @Override
    public void retweetedStatus(Status arg0){
        
    }
    
    @Override
    public void searched(QueryResult arg0){
        
    }
    
    @Override
    public void searchedPlaces(ResponseList<Place> arg0){
        
    }
    
    @Override
    public void searchedUser(ResponseList<User> arg0){
        
    }
    
    @Override
    public void sentDirectMessage(DirectMessage arg0){
        
    }
    
    @Override
    public void subscribedUserList(UserList arg0){
        
    }
    
    @Override
    public void unsubscribedUserList(UserList arg0){
        
    }
    
    @Override
    public void updatedAccountSettings(AccountSettings arg0){
        
    }
    
    @Override
    public void updatedFriendship(Relationship arg0){
        
    }
    
    @Override
    public void updatedProfile(User arg0){
        
    }
    
    @Override
    public void updatedProfileBackgroundImage(User arg0){
        
    }
    
    @Override
    public void updatedProfileBanner(){
        
    }
    
    @Override
    public void updatedProfileColors(User arg0){
        
    }
    
    @Override
    public void updatedProfileImage(User arg0){
        
    }
    
    @Override
    public void updatedStatus(Status arg0){
        
    }
    
    @Override
    public void updatedUserList(UserList arg0){
        
    }
    
    @Override
    public void verifiedCredentials(User arg0){
        
    }
    
}
