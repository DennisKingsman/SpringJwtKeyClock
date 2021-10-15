# Author
## DennisKingsman
# KeyCloak
Command to run KeyCloak on the custom port (8080 is default)  
Enter to cmd in the bin dir  
`sh ./standalone.sh -Djboss.http.port=8180`  
## Lesson 1
Create a new relm, new client, new user  
**Client settings:**  
Access type: confidential  
Set redirect url  
**User settings:**  
Set credentials  
**Spring boot project:**  
Create simple controller  
Configure yaml properies with spring security  
## Lesson 2
--
## Lesson 3 (client credentials flow)
**Client settings:**  
Access type: confidential  
Disable "Standart flow enabled"  
Enable "Service accounts enabled"  
No need to redirect uri  
**Spring boot project:**  
### Step 1
Create simple controller in mcs1  
Configure yaml properies with spring security  
Create and configure the SecurityConfig which extends WebSecurityConfigurerAdapter  
Run mcs1 and open postman  
Now u can get access token  
### Step 2 (mcs1 send token to mcs2 it's token relay pattern)
Create simple controller in mcs2  
Add RestTemplate \ WebClient to RestController mcs1  
Add call to mcs2 using:  
`@ResponseStatus(HttpStatus.OK)`  
`(Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()`  
`HttpHeaders` class  
restTemplate exchange method with  
`HttpEntity<>(httpHeaders)`  
and get the ResponseEntity  
**Config the authorization tab:**  
Set Token Name  
Type: OAuth 2  
Grant type: client credentials  
Set Access Token URL, Client ID, Client Secret  
Scope: openid  
Add authorization data to: Request headers  
Then send `get` request to `http://localhost:8083/mcs1/hello` and recieve response  
## Lesson 4
### Refresh token
Keycloak 12.0 + does not support Refresh Token for Client Credentials Grant  
**Try to get refresh token**  
**Config the authorization tab in postman:**  
Type: OAuth 2  
Set Token Name  
Add authorization data to: Request headers  
Grant type: Authorization code  
Callback URL: set the Valid Redirect URI from lesson one client  
Set Auth URL and Access Token URL using relm settings and Client ID, Client Secret using keycloak client  
Scope: openid  
Ckick on `get new access token` and enter keyclock user credentials  
U will get Access Token, refresh_token and id_token  
**Try to make a new** `post` **request in postman**  
**Config request**  
Body -> x-www-form and enter next <key, value>  
grant_type: refresh_token  
client_id: (ur client id)  
client_secret: (ur client secret)  
refresh_token: (ur refresh token)  
and click `send` then you will recieve new acces token, new refresh token and new id token  
If you add to scope: openid **offline_access** then you get non-expiring refresh token (not recommended)  
### Password grant flow  
Create a new client  
Access type: confidential  
Disable standart flow  
Direst access grant enable and save client  
Open a new tab in postman  
Config authorization section  
Grant Type: password credentials  
Set user's username and password and client's client_id and client_credentials  
###Issue 
**Step does not success**
Can't notice mistake  
# Resource 
[youtube-guide](https://www.youtube.com/playlist?list=PLSVW22jAG8pAXU0th247M7xPCekzeNdrH)  
