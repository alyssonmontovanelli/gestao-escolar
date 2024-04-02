import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final EscolaRepository escolaRepository;

    public ProfessorService(ProfessorRepository professorRepository, EscolaRepository escolaRepository) {
        this.professorRepository = professorRepository;
        this.escolaRepository = escolaRepository;
    }

    public List<Professor> listarPorEscola(Long idEscola) {
        Escola escola = buscarEscolaPorId(idEscola);
        return escola.getProfessores();
    }

    public Professor criarProfessor(Long idEscola, Professor professor) {
        Escola escola = buscarEscolaPorId(idEscola);

        // Verifica existencia da escola
        if (escola == null) {
            throw new ResourceNotFoundException("Escola com ID " + idEscola + " não existe");
        }

        // Verifica existencia de professor
        if (professor.getId() != null && professorRepository.existsById(professor.getId())) {
            throw new IllegalArgumentException("Já existe professor com ID informado");
        }

        // Verifica nome do professor
        if (professor.getNome() == null || professor.getNome().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do professor");
        }

        professor.setEscola(escola);
        return professorRepository.save(professor);
    }

    public void deletarProfessor(Long idEscola, Long idProfessor) {
        Professor professor = buscarProfessorPorId(idProfessor);

        // Verifica se o professor pertence à escola
        if (professor.getEscola().getId().equals(idEscola)) {
            professorRepository.delete(professor);
        } else {
            throw new ResourceNotFoundException("Professor não pertence à escola com o id: " + idEscola);
        }
    }

    private Escola buscarEscolaPorId(Long id) {
        return escolaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escola não encontrada com o id: " + id));
    }

    private Professor buscarProfessorPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + id));
    }

}