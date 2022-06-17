# FlowWorking
Flow 과제 제출

환경 
spring boot / h2 db / jpa 

** 코멘트 ** 
테스트 서버를 위해 최초 테스트 db를 firebase로 구축하였으나 NoSql기반 PK관리가 어려워
테스트용 인메모리 Db인 h2 db로 구축하였습니다. db 핸들링은 jpa방식을 사용하였습니다.

프로젝트 구조는 controller / service / repository / domain 으로 구성하였고 data 컬럼 정합성은
jpa의 hibernate에서 제공하는 validation 체크 방식을 사용하였습니다. 또한 최초 구동시 h2db 에서 
data.sql을 참조하여 테이블 및 기초 데이터를 생성합니다.

과제는 Front 화면과 서버로 이루어져 있으며 Front의 경우 최초 진입 시 데이터를 호출하여 화면에
main_yn값에 따라 checked 여부를, hashtag를 생성하게 하였습니다.

저장 시 front 객체 tag에 포함된 확장자인지를 1차적으로 체크하고 포함되지 않은 경우 서버로 요청 후
서버에서 중복을 체크 하여 사용자 Response객체에 코드 및 메세지를 세팅하여 Front로 리턴합니다.

또한, Controller에서 JPA 객체를 바인드하여 어노테이션으로 유효성을 체크하며 db 중복은 별도 함수를
구현하여 service 단 에서 체크합니다.

삭제의 경우 인식된 확장자 값을 키로 삭제합니다. 자세한 내용은 소 주석으로 대체하였습니다.
