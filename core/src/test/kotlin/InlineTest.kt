import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * (Default template)
 * Created on 2019-01-15
 *
 * @author Ha3
 */

inline class Item(val value: String)

interface Subsystem {
    fun generate(input: Item): Item
}

class SysCombiner(val subsystem: Subsystem) {
    fun combine(sysDiscard: Item, other: Item): Item = Item(subsystem.generate(sysDiscard).value + other.value)
}

class InlineTest {
    val subsystem: Subsystem = mock()
    val SUT: SysCombiner = SysCombiner(subsystem)

    @Test
    internal fun `it should dispatch generation`() {
        subsystem.stub {
            on { subsystem.generate(Item("discard")) }.thenReturn(Item("b"))
        }

        // Exercise
        val combine = SUT.combine(Item("discard"), Item("c"))

        // Verify
        assertThat(combine).isEqualTo(Item("bc"))
    }
}