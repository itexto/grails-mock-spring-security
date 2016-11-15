import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.Authentication

import org.springframework.security.test.context.TestSecurityContextHolder

/*
  Um serviço bem simples, apenas para simular o procedimento de
  autenticação usando o Spring Security Core plugin
*/
class MockSecurityService {
    /*
      Descrição dos parametros
      @attr usuario - Representa a classe de domínio que representa o usuário em seu projeto
      @attr permissoes - Lista de permissões do seu projeto baseado no Spring Security Core
    */
    def mockUsuario(Usuario usuario, List<Permissao> permissoes) {
      def auth = new MockAuth(usuario, permissoes)
      TestSecurityContextHolder.getContext().setAuthentication(auth)
    }

}

/*

*/
class MockAuth implements Authentication {

  private List<Permissao> permissoes
  private Usuario usuario

  MockAuth(Usuario usuario, List<Permissao> permissoes) {
    this.usuario = usuario
    this.permissoes = permissoes
  }

  public Object getCredentials() {
    /*
      No caso de um mock, você pode retornar nulo sem problemas
    */
    this.usuario.senha
  }

  Collection<? extends GrantedAuthority>	getAuthorities() {
    this.permissoes as Collection<GrantedAuthority>
  }

  Object	getDetails() {
    this.usuario
  }

  Object	getPrincipal() {
    this.usuario
  }

  String getName() {
    this.usuario.email
  }

  /*
    Se quiser simular autenticação, basta que você
    implemente os métodos isAuthenticated e setAuthenticated
    listados abaixo
  */
  boolean	isAuthenticated() {

    true
  }

  void	setAuthenticated(boolean isAuthenticated) {

  }

}
