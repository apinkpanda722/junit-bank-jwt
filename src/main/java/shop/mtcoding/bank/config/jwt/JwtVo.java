package shop.mtcoding.bank.config.jwt;

/*
 * SECRET키는 노출 되면 안된다. (클라우드 AWS - 환경변수, 파일에 있는 것을 읽을 수도 있고!)
 * Refresh Token은 이 프로젝트는 사용 안함
 */
public interface JwtVo {

    public static final String SECRET = "가상세계"; // HS256 (대칭키)
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 일주일
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
