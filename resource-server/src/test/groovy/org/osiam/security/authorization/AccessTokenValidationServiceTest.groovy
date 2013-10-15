package org.osiam.security.authorization

import org.codehaus.jackson.map.ObjectMapper
import org.osiam.helper.HttpClientHelper
import org.osiam.security.AuthenticationSpring
import org.osiam.security.AuthorizationRequestSpring
import org.osiam.security.OAuth2AuthenticationSpring
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: jochen
 * Date: 14.10.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
class AccessTokenValidationServiceTest extends Specification {

    def jacksonMapperMock = Mock(ObjectMapper)
    def httpClientHelperMock = Mock(HttpClientHelper)
    def accessTokenValidationService = new AccessTokenValidationService(mapper: jacksonMapperMock, httpClient: httpClientHelperMock)

    def "Inherit from springs ResourceServerTokenServices and override the method to load the Authentication depending on the given accessToken as String"() {
        given:
        def resultAsString = "the result"
        def oAuth2AuthenticationSpringMock = Mock(OAuth2AuthenticationSpring)

        when:
        def result = accessTokenValidationService.loadAuthentication("accessToken")

        then:
        1 * httpClientHelperMock.executeHttpGet("http://localhost:8080/osiam-auth-server/token/validate/accessToken") >> resultAsString
        1 * jacksonMapperMock.readValue(resultAsString, OAuth2AuthenticationSpring.class) >> oAuth2AuthenticationSpringMock
        1 * oAuth2AuthenticationSpringMock.getAuthenticationSpring() >> Mock(AuthenticationSpring)
        1 * oAuth2AuthenticationSpringMock.getAuthorizationRequestSpring() >> Mock(AuthorizationRequestSpring)
        result instanceof OAuth2Authentication
    }

    def "Should wrap the IOException from JacksonMapper to RuntimeException for loadAuthentication method"() {
        given:
        def resultAsString = "the result"

        when:
        accessTokenValidationService.loadAuthentication("accessToken")

        then:
        1 * httpClientHelperMock.executeHttpGet("http://localhost:8080/osiam-auth-server/token/validate/accessToken") >> resultAsString
        1 * jacksonMapperMock.readValue(resultAsString, OAuth2AuthenticationSpring.class) >> {throw new IOException()}
        thrown(RuntimeException)
    }

    def "Inherit from springs ResourceServerTokenServices and override the method read the OAuth2AccessToken depending on the given accessToken as String"() {
        given:
        def resultAsString = "the result"
        def oAuth2AccessTokenMock = Mock(OAuth2AccessToken)

        when:
        def result = accessTokenValidationService.readAccessToken("accessToken")

        then:
        1 * httpClientHelperMock.executeHttpGet("http://localhost:8080/osiam-auth-server/token/accessToken") >> resultAsString
        1 * jacksonMapperMock.readValue(resultAsString, OAuth2AccessToken.class) >> oAuth2AccessTokenMock
        result instanceof OAuth2AccessToken
    }

    def "Should wrap the IOException from JacksonMapper to RuntimeException for readAccessToken method"() {
        given:
        def resultAsString = "the result"

        when:
        accessTokenValidationService.readAccessToken("accessToken")

        then:
        1 * httpClientHelperMock.executeHttpGet("http://localhost:8080/osiam-auth-server/token/accessToken") >> resultAsString
        1 * jacksonMapperMock.readValue(resultAsString, OAuth2AccessToken.class) >> {throw new IOException()}
        thrown(RuntimeException)
    }
}