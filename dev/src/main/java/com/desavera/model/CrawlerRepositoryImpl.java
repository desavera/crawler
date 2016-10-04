@Repository
public class CrawlerRepositoryImpl implements CrawlerRepository {
 
    private static final String KEY = "Student";
     
    private RedisTemplate<String, Student> redisTemplate;
    private HashOperations hashOps;
 
    @Autowired
    private CrawlerRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
 
    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
     
    public void saveAnalysis(CrawlerAnalysis analysis) {
        hashOps.put(KEY, analysis.getURL(), analysis);
    }
 
    public Student findStudent(CrawlerAnalysis url) {
        return (CrawlerAnalysis) hashOps.get(KEY, url);
    }
 
    public Map<String, String> findAllAnalysis() {
        return hashOps.entries(KEY);
    }
}
