# OAuth2.0

## 授权码模式（authorization_code）

1. 资源拥有者打开客户端，客户端要求资源拥有者给予授权，它将浏览器被重定向到授权服务器，重定向时会
  附加客户端的身份信息。如：
     ```
     curl -X GET \
    'http://localhost:53020/uaa/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http%3A%2F%2Fwww.baidu.com'
     ```
     参数列表如下：
     - client_id：客户端准入标识
     - response_type：授权码模式固定为code
     - scope：客户端权限
     - redirect_uri：跳转uri，当授权码申请成功后会跳转到此地址，并在后边带上code参数（授权码）
2. 浏览器出现向授权服务器授权页面，之后将用户同意授权。
3. 授权服务器将授权码（AuthorizationCode）转经浏览器发送给client(通过redirect_uri)。
4. 客户端拿着授权码向授权服务器索要access_token，请求如下：
    ```
    curl -X POST \
    http://localhost:53020/uaa/oauth/token \
    -F client_id=c1 \
    -F client_secret=secret \
    -F grant_type=authorization_code \
    -F redirect_uri=http://www.baidu.com \
    -F code=RWDNcH
    ```
    参数列表如下
    - client_id：客户端准入标识。
    - client_secret：客户端秘钥。
    - grant_type：授权类型，填写authorization_code，表示授权码模式
    - code：授权码，就是刚刚获取的授权码，注意：授权码只使用一次就无效了，需要重新申请。
    - redirect_uri：申请授权码时的跳转url，一定和申请授权码时用的redirect_uri一致。
5. 授权服务器返回令牌(access_token)。

### 应用场景

授权码模式是四种模式中最安全的一种模式。

一般用于client是Web服务器端应用或第三方的原生App调用资源服务的时候。

因为在这种模式中access_token不会经过浏览器或移动端的App，而是直接从服务端去交换，这样就最大
限度的减小了令牌泄漏的风险。

## 简化模式（implicit）

1. 资源拥有者打开客户端，客户端要求资源拥有者给予授权，它将浏览器被重定向到授权服务器，重定向时会
   附加客户端的身份信息。如：
    ```
    curl -X GET \
    'http://localhost:53020/uaa/authorize?client_id=c1&response_type=token&scope=all&redirect_uri=http%3A%2F%2Fwww.baidu.com'
    ```
    参数描述同授权码模式 ，注意response_type=token，说明是简化模式。
     参数列表如下：
     - client_id：客户端准入标识
     - response_type：简化模式固定为token
     - scope：客户端权限
     - redirect_uri：跳转uri，当同意授权后会跳转到此地址，并在后边带上access_token（令牌）
2. 浏览器出现向授权服务器授权页面，之后将用户同意授权。
3. 授权服务器将令牌（access_token）以Hash的形式存放在重定向uri的fragment中发送给浏览器。如：
    ```
    https://www.baidu.com/#access_token=10f23f55-81a5-4629-82c5-cb0183b2361f&token_type=bearer&expires_in=7199
    ```

    **注**：
    - fragment 主要是用来标识 URI 所标识资源里的某个资源，在 URI 的末尾通过 （#）作为 fragment 的开头，
    其中 # 不属于 fragment 的值。
    - 如: https://domain/index#L18 这个 URI 中 L18 就是 fragment 的值。
    - 大家只需要知道js通过响应浏览器地址栏变化的方式能获取到 fragment 就行了。

### 应用场景

一般来说，简化模式用于没有服务器端的第三方单页面应用，因为没有服务器端就无法接收授权码。

## 密码模式（password）

1. 资源拥有者将用户名、密码发送给客户端。
2. 客户端拿着资源拥有者的用户名、密码向授权服务器请求令牌（access_token），请求如下：
    ```
    curl -X GET \
    'http://localhost:53020/uaa/oauth/token?client_id=c1&client_secret=secret&grant_type=password&username=huan&password=222'
    ```
    参数列表如下：
    - client_id：客户端准入标识。
    - client_secret：客户端秘钥。
    - grant_type：授权类型，填写password表示密码模式
    - username：资源拥有者用户名。
    - password：资源拥有者密码。
3. 授权服务器将令牌（access_token）发送给client。

### 应用场景

这种模式十分简单，但是却意味着直接将用户敏感信息泄漏给了client，因此这就说明这种模式只能用于client是我
们自己开发的情况下。

因此密码模式一般用于我们自己开发的，第一方原生App或第一方单页面应用。

## 客户端模式（client_credentials）

1. 客户端向授权服务器发送自己的身份信息，并请求令牌（access_token）。
2. 确认客户端身份无误后，将令牌（access_token）发送给client，请求如下：
    ```
    curl -X GET \
    'http://localhost:53020/uaa/oauth/token?client_id=c1&client_secret=secret&grant_type==client_credentials'
    ```
    参数列表如下：
    - client_id：客户端准入标识。
    - client_secret：客户端秘钥。
    - grant_type：授权类型，填写client_credentials表示客户端模式。

### 应用场景

这种模式是最方便但最不安全的模式。

因此这就要求我们对client完全的信任，而client本身也是安全的。

因此这种模式一般用来提供给我们完全信任的服务器端服务。比如，合作方系统对接，拉取一组用户信息。
