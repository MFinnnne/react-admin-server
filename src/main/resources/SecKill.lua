local userid = KEYS[1];
local prodid = KEYS[2];
local qtkey = "sk:" .. prodid .. ":qt";
local usersKey = "sk:" .. userid .. ":usr";
local userExists = redis.call("sismember", usersKey, userid);

if tonumber(userExists) == 1 then
    return 2;
end
local num = redis.call("get", qtkey);
if tonumber(num) <= 0 then
    return 0;
else
    redis.call("decr ", qtkey);
    redis.call("sadd ", usersKey, userid);
end
return 1;