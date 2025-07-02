Java.perform(function () {
    try {
        const LiveEntry = Java.use("com.shopee.live.LiveEntryActivity")
        LiveEntry.isLiveEligible.implementation = function () {
            console.log("[VOID] ✅ Bypass isLiveEligible ➤ true")
            return true
        }

        const Interceptor = Java.use("com.shopee.live.error.F00Interceptor")
        Interceptor.shouldShowF00.implementation = function () {
            console.log("[VOID] ❌ F00 Intercept blocked")
            return false
        }
    } catch (e) {
        console.log("[VOID] ⚠️ Hook error ➤", e)
    }
})

