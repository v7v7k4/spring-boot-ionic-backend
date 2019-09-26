package com.vivs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vivs.cursomc.domain.Cidade;
import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.domain.Endereco;
import com.vivs.cursomc.domain.enums.Perfil;
import com.vivs.cursomc.domain.enums.TipoCliente;
import com.vivs.cursomc.dto.ClienteDTO;
import com.vivs.cursomc.dto.ClienteNewDTO;
import com.vivs.cursomc.repositories.ClienteRepository;
import com.vivs.cursomc.repositories.EnderecoRepository;
import com.vivs.cursomc.security.UserSS;
import com.vivs.cursomc.services.exceptions.AuthorizationException;
import com.vivs.cursomc.services.exceptions.DataIntegrityException;
import com.vivs.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	//dependencia automaticamente instanciada pelo spring 
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Autowired
//	private S3Service s3service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		//se o usuário não é admin e se o id do parâmetro é diferente do id do usuário logado
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente categoria) {
		Cliente newObj = find(categoria.getId());
		updateData(newObj, categoria);
		return clienteRepository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
		
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cliente cli = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getComplemento(), 
				TipoCliente.toEnum(clienteDTO.getTipo()), bCryptPasswordEncoder.encode(clienteDTO.getSenha()));
		Cidade cid = new Cidade(clienteDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(),
				clienteDTO.getBairro(), clienteDTO.getCep(), cli, cid );
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(clienteDTO.getTelefone1());
		if(clienteDTO.getTelefone2() != null) {
			cli.getTelefones().add(clienteDTO.getTelefone2());
		}
		if(clienteDTO.getTelefone3() != null) {
			cli.getTelefones().add(clienteDTO.getTelefone3());
		}
		
		return cli;
	}
	
//	public URI uploadProfilePicture(MultipartFile multipartFile) {
//		UserSS user = UserService.authenticated();
//		if(user == null) {
//			throw new AuthorizationException("Acesso negado");
//		}
	
//	BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
//	jpgImage = imageService.cropSquare(jpgImage);
//	jpgImage = imageService.resize(jpgImage, size);
//	
//	String fileName = prefix + user.getId() + ".jpg";
//	return s3Service.upload(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
//	}

}
