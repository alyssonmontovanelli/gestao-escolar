import org.springframework.stereotype.Service;

@Service
public class EscolaService {

    private final EscolaRepository escolaRepository;

    public EscolaService(EscolaRepository escolaRepository) {
        this.escolaRepository = escolaRepository;
    }

    public List<Escola> listarTodas() {
        return escolaRepository.findAll();
    }

    public Escola buscarPorId(Long id) {
        return escolaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escola não encontrada com o id: " + id));
    }

    public Escola criarEscola(Escola escola) {
        // Verifica a existencia de ID
        if (escola.getId() != null && escolaRepository.existsById(escola.getId())) {
            throw new IllegalArgumentException("Já existe escola com o ID fornecido");
        }

        // Verifica nome da escola - nulo ou vazio
        if (escola.getNome() == null || escola.getNome().isEmpty()) {
            throw new IllegalArgumentException("É obrigatório informar o nome da escola");
        }

        return escolaRepository.save(escola);
    }
    public void deletarEscola(Long id) {
        Escola escola = buscarPorId(id);
        escolaRepository.delete(escola);
    }
}