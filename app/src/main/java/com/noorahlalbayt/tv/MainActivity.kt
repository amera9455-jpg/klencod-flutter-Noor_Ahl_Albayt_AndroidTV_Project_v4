package com.noorahlalbayt.tv

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val navy = Color.rgb(7, 20, 38)
    private val gold = Color.rgb(220, 181, 91)
    private val cream = Color.rgb(246, 240, 224)

    private data class Section(val title: String, val description: String)
    private val sections = listOf(
        Section("القرآن الكريم", "قسم مخصص للقرآن الكريم. ستضاف النصوص وبيانات القراءة بعد اختيار مصدر رقمي موثوق ومراجعته."),
        Section("أحاديث النبي ﷺ", "قسم أحاديث النبي محمد ﷺ. ستُضاف الأحاديث مع اسم المصدر والجزء والصفحة والطبعة."),
        Section("الروايات والأحاديث", "مجموعة أولية من النصوص التي طوبق لفظها مع مصادرها، مع بيانات الكتاب والباب والحديث والطبعة."),
        Section("الأدعية والزيارات", "قسم الأدعية والزيارات، مع ذكر الكتاب والموضع والطبعة عند توفرها."),
        Section("معرض الصور", "معرض التصاميم والصور الدينية المحفوظة داخل التطبيق."),
        Section("المصادر المعتمدة", "فهرس الكتب التي اختيرت لتكون مراجع مكتبة التطبيق.")
    )

    private val sources = listOf(
        "الكافي — الشيخ محمد بن يعقوب الكليني",
        "من لا يحضره الفقيه — الشيخ محمد بن علي بن بابويه الصدوق",
        "تهذيب الأحكام — الشيخ محمد بن الحسن الطوسي",
        "الاستبصار — الشيخ محمد بن الحسن الطوسي",
        "بحار الأنوار — العلامة محمد باقر المجلسي",
        "الوافي — الفيض الكاشاني",
        "نهج البلاغة — جمع الشريف الرضي",
        "الصحيفة السجادية — منسوبة إلى الإمام علي بن الحسين السجاد",
        "مستدرك الوسائل — الميرزا حسين النوري",
        "عيون أخبار الرضا — الشيخ الصدوق"
    )


    private data class Narration(
        val heading: String,
        val attribution: String,
        val text: String,
        val source: String,
        val note: String
    )

    private val narrations = listOf(
        Narration(
            "فضل طلب العلم",
            "عن أبي عبد الله الإمام الصادق عليه السلام، عن رسول الله صلى الله عليه وآله",
            "طَلَبُ الْعِلْمِ فَرِيضَةٌ عَلَى كُلِّ مُسْلِمٍ، أَلَا إِنَّ اللَّهَ يُحِبُّ بُغَاةَ الْعِلْمِ.",
            "الكافي، الشيخ الكليني، ج 1، كتاب فضل العلم، باب فرض العلم ووجوب طلبه والحث عليه، الحديث 1، ص 30 في طبعة دار الكتب الإسلامية، طهران، تحقيق علي أكبر الغفاري.",
            "أُثبت النص وموقعه في المصدر. لم نضع حكمًا رجاليًا على السند في هذه النسخة."
        ),
        Narration(
            "وجوب طلب العلم",
            "عن أبي عبد الله الإمام الصادق عليه السلام",
            "طَلَبُ الْعِلْمِ فَرِيضَةٌ.",
            "الكافي، الشيخ الكليني، ج 1، كتاب فضل العلم، باب فرض العلم ووجوب طلبه والحث عليه، الحديث 2، ص 30 في طبعة دار الكتب الإسلامية، طهران، تحقيق علي أكبر الغفاري.",
            "هذا هو لفظ الحديث الثاني في الباب. لا يُخلط بينه وبين الحديث الأول الأطول."
        ),
        Narration(
            "قيمة الإنسان",
            "قال أمير المؤمنين علي بن أبي طالب عليه السلام",
            "قِيمَةُ كُلِّ امْرِئٍ مَا يُحْسِنُهُ.",
            "نهج البلاغة، قسم الحكم، الحكمة 81. يظهر النص بهذا الرقم في النسخة المعروضة من نهج البلاغة؛ وقد يختلف ترقيم الحكم أو صفحات الطبعات الأخرى.",
            "هذه حكمة من نهج البلاغة، وليست حديثًا مسندًا في الكافي."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setBackgroundColor(navy)
        showHome()
    }

    private fun baseColumn(): LinearLayout = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(48, 30, 48, 28)
        setBackgroundColor(navy)
        layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    private fun label(value: String, size: Float, color: Int, bold: Boolean = false): TextView =
        TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
            if (bold) setTypeface(typeface, Typeface.BOLD)
            setPadding(12, 10, 12, 10)
        }

    private fun makeButton(caption: String, onActivate: () -> Unit): TextView =
        TextView(this).apply {
            text = "✦   $caption"
            textSize = 22f
            setTextColor(cream)
            setPadding(22, 16, 22, 16)
            gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
            isFocusable = true
            isFocusableInTouchMode = true
            background = android.graphics.drawable.GradientDrawable().apply {
                setColor(Color.rgb(19, 43, 68))
                cornerRadius = 14f
                setStroke(2, gold)
            }
            setOnClickListener { onActivate() }
            setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_UP &&
                    (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER)) {
                    onActivate()
                    true
                } else false
            }
        }

    private fun showHome() {
        val root = baseColumn()
        root.addView(label("نور أهل البيت", 34f, gold, true))
        root.addView(label("القرآن الكريم • الأحاديث • الروايات • الأدعية • الصور", 18f, cream))
        root.addView(label("اختر قسمًا باستخدام أزرار الاتجاهات في جهاز التحكم", 15f, gold))
        sections.forEach { section ->
            val item = makeButton(section.title) {
                when (section.title) {
                    "المصادر المعتمدة" -> showSources()
                    "الروايات والأحاديث" -> showNarrations()
                    else -> showSection(section.title, section.description)
                }
            }
            val params = LinearLayout.LayoutParams(-1, -2).apply {
                setMargins(0, 8, 0, 8)
            }
            root.addView(item, params)
        }
        val scroll = ScrollView(this).apply { addView(root) }
        setContentView(scroll)
        root.getChildAt(3).requestFocus()
    }

    private fun showSection(title: String, description: String) {
        val root = baseColumn()
        root.addView(label("نور أهل البيت", 30f, gold, true))
        root.addView(label(title, 26f, cream, true))
        root.addView(label(description, 21f, cream))
        root.addView(label("تنبيه: هذا القسم هيكل أولي، ولم تُضف إليه نصوص دينية غير متحقق منها.", 16f, gold))
        root.addView(makeButton("رجوع إلى الرئيسية") { showHome() })
        setContentView(ScrollView(this).apply { addView(root) })
        root.getChildAt(root.childCount - 1).requestFocus()
    }


    private fun showNarrations() {
        val root = baseColumn()
        root.addView(label("نور أهل البيت", 30f, gold, true))
        root.addView(label("الروايات والأحاديث الموثقة مبدئيًا", 25f, cream, true))
        root.addView(label("هذه مجموعة بداية صغيرة؛ ستُضاف نصوص أخرى بعد التحقق من ألفاظها وطبعاتها.", 16f, gold))
        narrations.forEachIndexed { index, item ->
            root.addView(label("${index + 1}. ${item.heading}", 22f, gold, true))
            root.addView(label(item.attribution, 17f, cream))
            root.addView(label("«${item.text}»", 22f, cream, true))
            root.addView(label("المصدر: ${item.source}", 15f, cream))
            root.addView(label("ملاحظة: ${item.note}", 14f, gold))
            root.addView(label("────────────────────────", 14f, gold))
        }
        root.addView(makeButton("رجوع إلى الرئيسية") { showHome() })
        setContentView(ScrollView(this).apply { addView(root) })
        root.getChildAt(root.childCount - 1).requestFocus()
    }

    private fun showSources() {
        val root = baseColumn()
        root.addView(label("نور أهل البيت", 30f, gold, true))
        root.addView(label("المصادر المعتمدة", 27f, cream, true))
        root.addView(label("فهرس المراجع المختارة لمكتبة التطبيق:", 18f, gold))
        sources.forEachIndexed { index, source ->
            root.addView(label("${index + 1}. $source", 19f, cream))
        }
        root.addView(label(
            "ملاحظة توثيقية: تختلف أرقام الأجزاء والصفحات باختلاف الطبعات. سيُسجل لكل نص اسم الكتاب والجزء والصفحة والطبعة بعد التحقق، ولن يُفترض تصحيح السند لمجرد وجود النص في الكتاب.",
            16f, gold
        ))
        root.addView(makeButton("رجوع إلى الرئيسية") { showHome() })
        setContentView(ScrollView(this).apply { addView(root) })
        root.getChildAt(root.childCount - 1).requestFocus()
    }
}
