@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{idEscola}")
    public List<Professor> listarProfessoresPorEscola(@PathVariable Long idEscola) {
        return professorService.listarPorEscola(idEscola);
    }
    @PostMapping("/{idEscola}")
    public ResponseEntity<Professor> criarProfessor(@PathVariable Long idEscola, @RequestBody Professor professor) {
        Professor novoProfessor = professorService.criarProfessor(idEscola, professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }
    @DeleteMapping("/{idEscola}/{idProfessor}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long idEscola, @PathVariable Long idProfessor) {
        professorService.deletarProfessor(idEscola, idProfessor);
        return ResponseEntity.noContent().build();
    }

}