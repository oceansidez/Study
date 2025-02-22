local key = KEYS[1] --限流KEY（一秒一个）"limit:" + System.currentTimeMillis() / 1000;
local limit = tonumber(ARGV[1]) --限流大小  10

local current = tonumber(redis.call('get', key) or "0")
if current + 1 > limit then --如果超出限流大小
    return 0
else  --请求数+1，并设置2秒过期
    redis.call("INCRBY", key, "1")
    redis.call("EXPIRE", key, "2")
    return 1
end