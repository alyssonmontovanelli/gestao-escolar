@RestController
@RequestMapping("/escolas")
public class EscolaController {

    @Autowired
    private EscolaService escolaService;

    @GetMapping
    public List<Escola> listarEscolas() {
        return escolaService.listarTodas();
    }
    @PostMapping
    public ResponseEntity<Escola> criarEscola(@RequestBody Escola escola) {
        Escola novaEscola = escolaService.criarEscola(escola);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEscola);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEscola(@PathVariable Long id) {
        escolaService.deletarEscola(id);
        return ResponseEntity.noContent().build();
    }
}
