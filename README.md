# Author
## DennisKingsman
# KeyClock
Command to run KeyClock on the custom port (8080 is default)  
Enter in the bin dir  
`sh ./standalone.sh -Djboss.http.port=8180`  
## Lesson 1
Create a new relm, new client, new user  
Client settings:  
Access type: confidential  
Set redirect url  
User settings:  
Set credentials  
Spring boot project:  
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

