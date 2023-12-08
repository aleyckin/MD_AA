import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.R
import com.example.myapplication.database.ImageConverter
import com.example.myapplication.database.dao.CardDao
import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.database.entities.Card
import com.example.myapplication.database.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class, Card::class], version = 1, exportSchema = false)
@TypeConverters(ImageConverter::class)
abstract class MobileAppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cardDao(): CardDao

    companion object{
        private const val DB_NAME: String = "my-db"

        @Volatile
        private var INSTANCE: MobileAppDataBase? = null

        suspend fun initialDataBase(appContext: Context){
            INSTANCE?.let { database ->
                val userDao = database.userDao()
                userDao.insert(User(id = 1, login = "Иван", password = "1234",))
                userDao.insert(User(id = 2, login = "Леонель Месси", password = "4321",))

                val cardDao = database.cardDao()
                cardDao.insert(Card(name = "Феррари Имба", location = "г. Ульяновск",
                    image = BitmapFactory.decodeResource(appContext.resources, R.drawable.ferrari_laferrari_car2), mileage = 7322, price = 125000, userId = 1)
                )
                cardDao.insert(Card(name = "Москоувич", location = "г. Москва",
                    image = BitmapFactory.decodeResource(appContext.resources, R.drawable.moscowich_car3), mileage = 156771, price = 1000, userId = 1)
                )
            }
        }

        fun getInstance(appContext: Context): MobileAppDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    appContext,
                    MobileAppDataBase::class.java,
                    DB_NAME
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                initialDataBase(appContext)
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}