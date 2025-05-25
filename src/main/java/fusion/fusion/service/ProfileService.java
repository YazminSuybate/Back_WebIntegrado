package fusion.fusion.service;


import fusion.fusion.io.ProfileRequest;
import fusion.fusion.io.ProfileResponse;

public interface ProfileService {


    ProfileResponse createProfile(ProfileRequest request);

    ProfileResponse getProfile(String email);


}
